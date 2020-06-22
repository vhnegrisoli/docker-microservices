package br.com.dge.produtoapi.modulos.produto.repository;

import br.com.dge.produtoapi.modulos.produto.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Boolean existsByCnpj(String cnpj);

    Boolean existsByCnpjAndIdNot(String cnpj, Integer id);

    Boolean existsByRazaoSocial(String razaoSocial);

    Boolean existsByRazaoSocialAndIdNot(String razaoSocial, Integer id);

    List<Fornecedor> findByRazaoSocialContaining(String razaoSocial);

    List<Fornecedor> findByCnpjContaining(String cnpj);
}
