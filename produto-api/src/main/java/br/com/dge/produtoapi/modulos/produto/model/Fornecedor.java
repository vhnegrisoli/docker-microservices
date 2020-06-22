package br.com.dge.produtoapi.modulos.produto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FORNECEDOR")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CNPJ", nullable = false, length = 20)
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL", nullable = false, length = 50)
    private String razaoSocial;
}
