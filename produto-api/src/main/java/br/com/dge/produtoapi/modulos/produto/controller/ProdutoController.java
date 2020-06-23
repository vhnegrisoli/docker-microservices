package br.com.dge.produtoapi.modulos.produto.controller;

import br.com.dge.produtoapi.modulos.produto.dto.PedidoRequest;
import br.com.dge.produtoapi.modulos.produto.dto.PedidoResponse;
import br.com.dge.produtoapi.modulos.produto.dto.VendasProdutoResponse;
import br.com.dge.produtoapi.modulos.produto.model.Produto;
import br.com.dge.produtoapi.modulos.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("{id}")
    public Produto buscarPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodosProdutos();
    }

    @GetMapping("em-estoque")
    public List<Produto> buscarTodosDisponiveisEmEstoque() {
        return produtoService.buscarProdutosDisponiveis();
    }

    @GetMapping("descricao/{descricao}")
    public List<Produto> buscarPorDescricao(@PathVariable String descricao) {
        return produtoService.buscarPorDescricao(descricao);
    }

    @GetMapping("validar-estoque")
    public void validarProdutoEstoque(@RequestParam Integer id,
                            @RequestParam Integer qtdDesejada) {
        produtoService.validarProdutoEstoque(id, qtdDesejada);
    }

    @PutMapping("validar-estoque-varios")
    public void validarEstoqueVarios(@RequestBody PedidoRequest request) {
        produtoService.validarEstoqueVariosProdutos(request);
    }

    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @PutMapping("{id}")
    public Produto salvarProduto(@RequestBody Produto produto, @PathVariable Integer id) {
        return produtoService.alterarProduto(produto, id);
    }

    @GetMapping("razao-social/{razaoSocial}")
    public List<Produto> buscarPorRazaoSocial(@PathVariable String razaoSocial) {
        return produtoService.buscarPorRazaoSocial(razaoSocial);
    }

    @GetMapping("cnpj/{cnpj}")
    public List<Produto> buscarPorCnpj(@PathVariable String cnpj) {
        return produtoService.buscarPorCnpj(cnpj);
    }

    @GetMapping("categoria/{descricao}")
    public List<Produto> buscarPorDescricaoCategoria(@PathVariable String descricao) {
        return produtoService.buscarPorCategoria(descricao);
    }

    @GetMapping("vendas-por-produto/{id}")
    public VendasProdutoResponse buscarVendasDoProduto(@PathVariable Integer id, HttpServletRequest request) {
        return produtoService.buscarVendasDoProduto(id, request.getHeader("Authorization"));
    }

    @PostMapping("informacoes-pedido")
    public List<PedidoResponse> buscarInformacoesDoPedido(@RequestBody PedidoRequest pedido) {
        return produtoService.buscarInformacoesDoPedido(pedido);
    }
}