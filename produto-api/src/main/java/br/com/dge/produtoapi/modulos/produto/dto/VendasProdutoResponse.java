package br.com.dge.produtoapi.modulos.produto.dto;

import br.com.dge.produtoapi.modulos.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendasProdutoResponse {

    private Produto produto;
    private Integer qtdVendas;
}