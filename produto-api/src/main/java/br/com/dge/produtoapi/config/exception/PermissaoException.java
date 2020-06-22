package br.com.dge.produtoapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PermissaoException extends RuntimeException {

    public PermissaoException(String message) {
        super(message);
    }
}
