package br.com.dge.produtoapi.modulos.produto.repository;

import br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade;
import br.com.dge.produtoapi.modulos.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByDescricaoContaining(String descricao);

    List<Produto> findByCategoriaIdIn(List<Integer> categoriasIds);

    List<Produto> findByFornecedorIdIn(List<Integer> fornecedoresIds);

    List<Produto> findByDisponibilidadeAndQtdEstoqueGreaterThan(EDisponibilidade disponibilidade, Integer qtdEstoque);

    Boolean existsByDescricao(String descricao);

    Boolean existsByDescricaoAndIdNot(String descricao, Integer id);
}
