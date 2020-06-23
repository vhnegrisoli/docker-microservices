package br.com.dge.produtoapi.modulos.produto.dto;

import br.com.dge.produtoapi.modulos.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoResponse {

    private Produto produto;
    private Integer qtdItens;
}
