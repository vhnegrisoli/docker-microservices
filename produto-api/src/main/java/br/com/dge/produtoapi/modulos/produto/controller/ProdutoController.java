package br.com.dge.produtoapi.modulos.produto.controller;

import br.com.dge.produtoapi.modulos.produto.dto.VendasProdutoResponse;
import br.com.dge.produtoapi.modulos.produto.model.Produto;
import br.com.dge.produtoapi.modulos.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void buscarPorId(@RequestParam Integer id,
                            @RequestParam Integer qtdDesejada) {
        produtoService.validarProdutoEstoque(id, qtdDesejada);
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
    public VendasProdutoResponse buscarVendasDoProduto(@PathVariable Integer id) {
        return produtoService.buscarVendasDoProduto(id);
    }
}