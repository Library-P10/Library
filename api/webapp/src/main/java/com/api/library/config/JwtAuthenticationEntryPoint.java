package com.api.library.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Cette classe étendra la classe AuthenticationEntryPoint de Spring et remplacera sa méthode pour commencer.
//        Il rejette toutes les demandes non authentifiées et envoie le code d'erreur 401.

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
    }
}
