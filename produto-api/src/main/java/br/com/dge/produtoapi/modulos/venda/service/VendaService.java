package br.com.dge.produtoapi.modulos.venda.service;

import br.com.dge.produtoapi.config.exception.PermissaoException;
import br.com.dge.produtoapi.config.exception.ValidacaoException;
import br.com.dge.produtoapi.modulos.venda.client.VendaClient;
import br.com.dge.produtoapi.modulos.venda.dto.ProdutoQtdVendaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaClient vendaClient;

    public void tokenValida(String token) {
        try {
            vendaClient.checkToken(token);
        } catch (Exception ex) {
            throw new PermissaoException("Usuário não autenticado.");
        }
    }

    public ProdutoQtdVendaResponse buscarQuantidadeDeVendasDeUmProduto(Integer produtoId) {
        try {
            return vendaClient.buscarQuantidadeDeVendasDeUmProduto(produtoId);
        } catch (Exception ex) {
            throw new ValidacaoException("Não foi possível buscar as vendas do produto.");
        }
    }
}
