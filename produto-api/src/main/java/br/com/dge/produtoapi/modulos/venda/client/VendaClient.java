package br.com.dge.produtoapi.modulos.venda.client;

import br.com.dge.produtoapi.modulos.venda.dto.ProdutoQtdVendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vendaClient",
    url = "${app-config.services.venda.url}")
public interface VendaClient {

    @PostMapping("/auth/check_token")
    void checkToken(@RequestParam String token);

    @GetMapping("/api/vendas/produto/{produtoId}")
    ProdutoQtdVendaResponse buscarQuantidadeDeVendasDeUmProduto(@PathVariable Integer produtoId);
}
