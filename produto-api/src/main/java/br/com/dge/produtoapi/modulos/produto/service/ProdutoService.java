package br.com.dge.produtoapi.modulos.produto.service;

import br.com.dge.produtoapi.config.ValidacaoException;
import br.com.dge.produtoapi.modulos.produto.model.Produto;
import br.com.dge.produtoapi.modulos.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade.DISPONIVEL;
import static br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade.INDISPONIVEL;

@Service
public class ProdutoService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProdutoRepository produtoRepository;

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
    public void atualizarEstoqueProduto(Integer id, Integer qtdPedida) {
        validarProdutoEstoque(id, qtdPedida);
        var produto = buscarPorId(id);
        produto.setQtdEstoque(produto.getQtdEstoque() - qtdPedida);
        atualizarDispinibilidade(produto);
        produtoRepository.save(produto);
    }

    private void atualizarDispinibilidade(Produto produto) {
        if (produto.isEstoqueZerado() && produto.isDisponivel()) {
            produto.setDisponibilidade(INDISPONIVEL);
        }
    }
}