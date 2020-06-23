package br.com.dge.produtoapi.modulos.produto.dto;

import lombok.Data;

@Data
public class ProdutoEstoqueRequest {

    private Integer produtoId;
    private Integer qtdDesejada;
}
