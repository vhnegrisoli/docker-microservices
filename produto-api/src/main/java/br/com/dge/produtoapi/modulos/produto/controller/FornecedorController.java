package br.com.dge.produtoapi.modulos.produto.controller;

import br.com.dge.produtoapi.modulos.produto.model.Fornecedor;
import br.com.dge.produtoapi.modulos.produto.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public Fornecedor salvarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.salvarFornecedor(fornecedor);
    }

    @PutMapping("{id}")
    public Fornecedor editarFornecedor(@RequestBody Fornecedor fornecedor, @PathVariable Integer id) {
        return fornecedorService.editarFornecedor(fornecedor, id);
    }

    @GetMapping
    public List<Fornecedor> buscarTodos() {
        return fornecedorService.buscarTodos();
    }

    @GetMapping("{id}")
    public Fornecedor buscarPorId(@PathVariable Integer id) {
        return fornecedorService.buscarPorId(id);
    }

    @GetMapping("cnpj/{cnpj}")
    public List<Fornecedor> buscarPorCnpj(@PathVariable String cnpj) {
        return fornecedorService.buscarPorCnpj(cnpj);
    }

    @GetMapping("razao-social/{razaoSocial}")
    public List<Fornecedor> buscarPorRazaoSocial(@PathVariable String razaoSocial) {
        return fornecedorService.buscarPorRazaoSocial(razaoSocial);
    }
}
