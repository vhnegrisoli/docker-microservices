package br.com.dge.produtoapi.modulos.produto.controller;

import br.com.dge.produtoapi.modulos.produto.model.Categoria;
import br.com.dge.produtoapi.modulos.produto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public Categoria salvarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.salvarCategoria(categoria);
    }

    @PutMapping("{id}")
    public Categoria editarCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
        return categoriaService.editarCategoria(categoria, id);
    }

    @GetMapping
    public List<Categoria> buscarTodas() {
        return categoriaService.buscarTodas();
    }

    @GetMapping("{id}")
    public Categoria buscarPorId(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id);
    }

    @GetMapping("descricao/{descricao}")
    public List<Categoria> buscarPorDescricao(@PathVariable String descricao) {
        return categoriaService.buscarPorDescricao(descricao);
    }
}
