package br.com.dge.produtoapi.modulos.venda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "vendaClient",
    url = "${app-config.services.venda.url}")
public interface VendaClient {

    @PostMapping("/auth/check_token")
    void checkToken(@RequestParam String token);

    @GetMapping("/api/vendas/produto/{produtoId}")
    List<String> buscarVendasDoProduto(@PathVariable Integer produtoId,
                                       @RequestHeader("authorization") String authorization);
}
