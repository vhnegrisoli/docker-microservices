package br.com.dge.produtoapi.modulos.produto.model;

import br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.dge.produtoapi.modulos.produto.enums.EDisponibilidade.DISPONIVEL;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTO")
public class Produto {

    private static final Integer ZERO = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRICAO", nullable = false, length = 50)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "FK_FORNECEDOR", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column(name = "QTD_ESTOQUE", nullable = false, length = 5)
    private Integer qtdEstoque;

    @Column(name = "DISPONIBILIDADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private EDisponibilidade disponibilidade;

    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        disponibilidade = DISPONIVEL;
        dataCadastro = LocalDateTime.now();
    }

    public boolean isEstoqueZerado() {
        return qtdEstoque.equals(ZERO);
    }

    public boolean isEstoqueNegativo() {
        return qtdEstoque < ZERO;
    }

    public boolean isDisponivel() {
        return disponibilidade.equals(DISPONIVEL);
    }
}
