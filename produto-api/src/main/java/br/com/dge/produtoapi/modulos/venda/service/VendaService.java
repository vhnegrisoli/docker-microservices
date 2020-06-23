package br.com.dge.produtoapi.modulos.venda.service;

import br.com.dge.produtoapi.config.exception.PermissaoException;
import br.com.dge.produtoapi.config.exception.ValidacaoException;
import br.com.dge.produtoapi.modulos.venda.client.VendaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaClient vendaClient;

    public void tokenValida(String token) {
        try {
            vendaClient.checkToken(token);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PermissaoException("Usuário não autenticado.");
        }
    }

    public List<String> buscarQuantidadeDeVendasDeUmProduto(Integer produtoId, String token) {
        try {
            System.out.println(token);
            return vendaClient.buscarVendasDoProduto(produtoId, token);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidacaoException("Não foi possível buscar as vendas do produto.");
        }
    }
}
