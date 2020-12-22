package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.Customer;
import com.web.library.weblibrary.proxies.CustomerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SessionFilter implements Filter {

    @Autowired
    private CustomerProxy customerProxy;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Récupération de l'JSessionID
        HttpSession httpSession = request.getSession(true);
        Cookie[] cookies = request.getCookies();


        if (httpSession.getAttribute("customer")==null){
            if (cookies!=null) {
                for (final Cookie cookie : cookies) {
                    if (cookie.getName().equals("Token")) {
                        String token = cookie.getValue();
                        Customer customer = customerProxy.getInfoCustomer("Bearer "+token);
                        httpSession.setAttribute("customer", customer);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            // Si Pas de token ni JSessionID -> Oriente sur books
            if ( request.getServletPath().startsWith("/authenticate") ||
                    request.getServletPath().startsWith("/book") ||
                    request.getServletPath().startsWith("/books") ||
                    request.getServletPath().equals("/authentication") ||
                    request.getServletPath().equals("/users") ||
                    request.getServletPath().startsWith("/css") ||
                    request.getServletPath().startsWith("/img") ||
                    request.getServletPath().equals("/registration")){
                chain.doFilter(request, response);
                return;
            }

            response.sendRedirect("/books");
                return;
        }else {
            // Si j'ai un JSessionsID -> Je fais rien DoFilter.
            chain.doFilter(request, response);
            return;
        }
    }


    @Override
    public void destroy() {

    }
}
