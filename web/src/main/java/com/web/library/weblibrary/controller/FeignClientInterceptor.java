package com.web.library.weblibrary.controller;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();

        Cookie[] cookies = request.getCookies();
        for (final Cookie cookie : cookies) {
            if(cookie.getName().equals("Token")){
                String token = cookie.getValue();
                requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_TYPE+" "+ token);
                return;
            }
        }
    }
}