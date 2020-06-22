package br.com.dge.produtoapi.modulos.rabbitmq.dto;

import lombok.Data;

@Data
public class ProdutoEstoqueRequest {

    private Integer produtoId;
    private Integer qtdDesejada;
}
