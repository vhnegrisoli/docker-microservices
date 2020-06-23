package br.com.dge.produtoapi.modulos.produto.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {

    private List<ProdutoEstoqueRequest> produtos;
}
