package br.com.dge.produtoapi.modulos.produto.dto;

import br.com.dge.produtoapi.modulos.produto.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendasProdutoResponse {

    private Produto produto;
    List<String> vendasIds;
    private Integer qtdVendas;
}