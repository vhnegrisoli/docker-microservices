package br.com.dge.produtoapi.modulos.produto.service;

import br.com.dge.produtoapi.config.exception.ValidacaoException;
import br.com.dge.produtoapi.modulos.produto.model.Fornecedor;
import br.com.dge.produtoapi.modulos.produto.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
        validarFornecedorCnpjRazaoSocialExistente(fornecedor);
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public Fornecedor editarFornecedor(Fornecedor fornecedor, Integer id) {
        validarFornecedorExistenteEditar(fornecedor, id);
        fornecedor.setId(id);
        return fornecedorRepository.save(fornecedor);
    }

    private void validarFornecedorCnpjRazaoSocialExistente(Fornecedor fornecedor) {
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new ValidacaoException("O cnpj " + fornecedor.getCnpj() + " já existe.");
        }
        if (fornecedorRepository.existsByRazaoSocial(fornecedor.getRazaoSocial())) {
            throw new ValidacaoException("A razão social " + fornecedor.getRazaoSocial() + " já existe.");
        }
    }

    private void validarFornecedorExistenteEditar(Fornecedor fornecedor, Integer id) {
        if (fornecedorRepository.existsByCnpjAndIdNot(fornecedor.getCnpj(), id)) {
            throw new ValidacaoException("O cnpj " + fornecedor.getCnpj() + " já existe.");
        }
        if (fornecedorRepository.existsByRazaoSocialAndIdNot(fornecedor.getRazaoSocial(), id)) {
            throw new ValidacaoException("A razão social " + fornecedor.getRazaoSocial() + " já existe.");
        }
    }

    public List<Fornecedor> buscarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Integer id) {
        return fornecedorRepository.findById(id)
            .orElseThrow(() -> new ValidacaoException("O fornecedor não existe."));
    }

    public List<Fornecedor> buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpjContaining(cnpj);
    }

    public List<Fornecedor> buscarPorRazaoSocial(String razaoSocial) {
        return fornecedorRepository.findByRazaoSocialContaining(razaoSocial);
    }
}
