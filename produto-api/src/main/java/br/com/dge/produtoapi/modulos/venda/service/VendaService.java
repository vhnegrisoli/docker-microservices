package br.com.dge.produtoapi.modulos.venda.service;

import br.com.dge.produtoapi.config.exception.PermissaoException;
import br.com.dge.produtoapi.modulos.venda.client.VendaClient;
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
}
