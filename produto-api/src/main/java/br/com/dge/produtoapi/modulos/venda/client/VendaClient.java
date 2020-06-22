package br.com.dge.produtoapi.modulos.venda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vendaClient",
    url = "${app-config.services.venda.url}")
public interface VendaClient {

    @GetMapping("/check_token")
    void checkToken(@RequestParam String token);
}
