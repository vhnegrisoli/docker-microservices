package br.com.dge.produtoapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestPermissaoExceptionHandler {

    @ExceptionHandler(PermissaoException.class)
    public ResponseEntity<?> handleResouseNotFoundException(PermissaoException rfnException) {
        ExceptionDetails resourceNotFoundDetails = new ExceptionDetails();
        resourceNotFoundDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
        resourceNotFoundDetails.setMessage(rfnException.getMessage());
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.UNAUTHORIZED);
    }
}
