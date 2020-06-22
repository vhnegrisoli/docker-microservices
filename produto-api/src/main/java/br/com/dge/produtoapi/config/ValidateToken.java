package br.com.dge.produtoapi.config;

import br.com.dge.produtoapi.config.exception.PermissaoException;
import br.com.dge.produtoapi.modulos.venda.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class ValidateToken extends HandlerInterceptorAdapter {

    private static final String PROTECTED_API = "api/";
    private static final String AUTHORIZATION = "authorization";
    private static final String BEARER = "bearer ";
    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Autowired
    private VendaService vendaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getRequestURI().contains(PROTECTED_API)) {
            vendaService.tokenValida(recuperarTokenDoHeader(request));
        }
        return true;
    }

    private String recuperarTokenDoHeader(HttpServletRequest request) {
        if (isEmpty(request.getHeader(AUTHORIZATION))) {
            throw new PermissaoException("É necessário informar um token de acesso.");
        }
        var bearerToken = request.getHeader(AUTHORIZATION);
        if (!bearerToken.toLowerCase().contains(BEARER) || bearerToken.split(EMPTY_SPACE).length == 1) {
            throw new PermissaoException("É necessário informar o token completo.");
        }
        return bearerToken.split(EMPTY_SPACE)[TOKEN_INDEX];
    }
}
