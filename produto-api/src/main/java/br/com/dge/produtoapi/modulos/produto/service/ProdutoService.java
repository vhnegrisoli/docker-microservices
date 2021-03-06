package br.com.dge.produtoapi.modulos.produto.service;

import br.com.dge.produtoapi.config.exception.ValidacaoException;
import br.com.dge.produtoapi.modulos.produto.dto.PedidoRequest;
import br.com.dge.produtoapi.modulos.produto.dto.PedidoResponse;
import br.com.dge.produtoapi.modulos.produto.dto.VendasProdutoResponse;
import br.com.dge.produtoapi.modulos.produto.model.Categoria;
import br.com.dge.produtoapi.modulos.produto.model.Fornecedor;
import br.com.dge.produtoapi.modulos.produto.model.Produto;
import br.com.dge.produtoapi.modulos.produto.repository.ProdutoRepository;
import br.com.dge.produtoapi.modulos.rabbitmq.dto.ProdutoEstoqueRequest;
import br.com.dge.produtoapi.modulos.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade.DISPONIVEL;
import static br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade.INDISPONIVEL;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProdutoService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private VendaService vendaService;

    @Transactional
    public Produto salvarProduto(Produto produto) {
        validarEstoqueZerado(produto);
        validarEstoqueNegativo(produto);
        validarDescricaoExistente(produto.getDescricao());
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto alterarProduto(Produto produto, Integer id) {
        validarEstoqueZerado(produto);
        validarEstoqueNegativo(produto);
        validarDescricaoExistenteEditar(id, produto.getDescricao());
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    private void validarEstoqueZerado(Produto produto) {
        if (produto.isEstoqueZerado()) {
            throw new ValidacaoException("Não é possível salvar um produto com estoque zerado.");
        }
    }

    private void validarEstoqueNegativo(Produto produto) {
        if (produto.isEstoqueNegativo()) {
            throw new ValidacaoException("Não é possível salvar um produto com estoque negativo.");
        }
    }

    private void validarDescricaoExistente(String descricao) {
        if (produtoRepository.existsByDescricao(descricao))  {
            throw new ValidacaoException("Já existe um produto com esta descrição.");
        }
    }

    private void validarDescricaoExistenteEditar(Integer id, String descricao) {
        if (produtoRepository.existsByDescricaoAndIdNot(descricao, id))  {
            throw new ValidacaoException("Já existe um produto com esta descrição.");
        }
    }

    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> buscarProdutosDisponiveis() {
        return produtoRepository.findByDisponibilidadeAndQtdEstoqueGreaterThan(DISPONIVEL, ZERO);
    }

    public Produto buscarPorId(Integer id) {
        return  produtoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O produto não existe."));
    }

    public List<Produto> buscarPorDescricao(String descricao) {
        return produtoRepository.findByDescricaoContaining(descricao);
    }

    public void validarEstoqueVariosProdutos(PedidoRequest request) {
        request.getProdutos()
            .forEach(produto -> validarProdutoEstoque(produto.getProdutoId(), produto.getQtdDesejada()));
    }

    public void validarProdutoEstoque(Integer id, Integer qtdDesejada) {
        var produto = buscarPorId(id);
        if (qtdDesejada <= ZERO) {
            throw new ValidacaoException("Por favor, informe um valor acima de 0.");
        }
        if (produto.getQtdEstoque() < qtdDesejada) {
            throw new ValidacaoException("Não é possível realizar a compra pois o estoque atual do produto "
                + produto.getDescricao()
                + " é de "
                + produto.getQtdEstoque()
                + " itens.");
        }
    }

    @Transactional
    public void atualizarEstoqueProduto(ProdutoEstoqueRequest request) {
        var produto = buscarPorId(request.getProdutoId());
        if (request.isReduzirEstoque()) {
            validarProdutoEstoque(request.getProdutoId(), request.getQtdDesejada());
            produto.setQtdEstoque(produto.getQtdEstoque() - request.getQtdDesejada());
        } else {
            produto.setQtdEstoque(produto.getQtdEstoque() + request.getQtdDesejada());
        }
        atualizarDispinibilidade(produto);
        produtoRepository.save(produto);
    }

    private void atualizarDispinibilidade(Produto produto) {
        if (produto.isEstoqueZerado() && produto.isDisponivel()) {
            produto.setDisponibilidade(INDISPONIVEL);
        }
    }

    public List<Produto> buscarPorCnpj(String cnpj) {
        var fornecedores = fornecedorService.buscarPorCnpj(cnpj);
        if (!isEmpty(fornecedores)) {
            return produtoRepository
                .findByFornecedorIdIn(fornecedores
                    .stream()
                    .map(Fornecedor::getId)
                    .collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public List<Produto> buscarPorRazaoSocial(String razaoSocial) {
        var fornecedores = fornecedorService.buscarPorRazaoSocial(razaoSocial);
        if (!isEmpty(fornecedores)) {
            return produtoRepository
                .findByFornecedorIdIn(fornecedores
                    .stream()
                    .map(Fornecedor::getId)
                    .collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public List<Produto> buscarPorCategoria(String descricao) {
        var categorias = categoriaService.buscarPorDescricao(descricao);
        if (!isEmpty(categorias)) {
            return produtoRepository
                .findByCategoriaIdIn(categorias
                    .stream()
                    .map(Categoria::getId)
                    .collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public VendasProdutoResponse buscarVendasDoProduto(Integer id, String token) {
        var produto = buscarPorId(id);
        var vendasProduto = vendaService.buscarQuantidadeDeVendasDeUmProduto(id, token);
        return new VendasProdutoResponse(produto, vendasProduto, vendasProduto.size());
    }

    public List<PedidoResponse> buscarInformacoesDoPedido(PedidoRequest request) {
        return request.getProdutos()
            .stream()
            .map(pedido -> new PedidoResponse(buscarPorId(pedido.getProdutoId()), pedido.getQtdDesejada()))
            .collect(Collectors.toList());
    }
}