package br.com.dge.produtoapi.config;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private int status;
    private String message;
}
