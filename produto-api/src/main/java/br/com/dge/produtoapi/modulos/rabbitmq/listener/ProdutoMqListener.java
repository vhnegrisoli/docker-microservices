package br.com.dge.produtoapi.modulos.rabbitmq.listener;

import br.com.dge.produtoapi.modulos.produto.service.ProdutoService;
import br.com.dge.produtoapi.modulos.rabbitmq.dto.ProdutoEstoqueRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProdutoMqListener {

    @Autowired
    private ProdutoService produtoService;

    @RabbitListener(queues = "${app-config.queue.produto-atualizar-estoque}")
    public void receberAtualizacaoEstoqueDaFila(ProdutoEstoqueRequest request) {
        log.info("Mensagem recebida: " + request);
        try {
            produtoService.atualizarEstoqueProduto(request);
        } catch (Exception ex) {
            log.info("Erro ao atualizar produto: " + request.getProdutoId());
            ex.printStackTrace();
        }
    }
}
