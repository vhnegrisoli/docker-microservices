package br.com.dge.produtoapi.modulos.produto.repository;

import br.com.dge.produtoapi.modulos.produto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Boolean existsByDescricao(String descricao);

    Boolean existsByDescricaoAndIdNot(String descricao, Integer id);

    List<Categoria> findByDescricaoContaining(String descricao);
}
