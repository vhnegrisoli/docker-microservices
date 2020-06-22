package br.com.dge.produtoapi.modulos.produto.service;

import br.com.dge.produtoapi.config.ValidacaoException;
import br.com.dge.produtoapi.modulos.produto.model.Categoria;
import br.com.dge.produtoapi.modulos.produto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvarCategoria(Categoria categoria) {
        validarCategoriaDescricaoExistente(categoria.getDescricao());
        return categoriaRepository.save(categoria);
    }

    public Categoria editarCategoria(Categoria categoria, Integer id) {
        validarCategoriaExistenteEditar(categoria.getDescricao(), id);
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    private void validarCategoriaDescricaoExistente(String descricao) {
        if (categoriaRepository.existsByDescricao(descricao)) {
            throw new ValidacaoException("A categoria " + descricao + " já existe.");
        }
    }

    private void validarCategoriaExistenteEditar(String descricao, Integer id) {
        if (categoriaRepository.existsByDescricaoAndIdNot(descricao, id)) {
            throw new ValidacaoException("A categoria " + descricao + " já existe.");
        }
    }

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("A categoria não existe."));
    }

    public List<Categoria> buscarPorDescricao(String descricao) {
        return categoriaRepository.findByDescricaoContaining(descricao);
    }
}
