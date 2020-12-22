package com.api.library.config;

import com.api.library.service.contract.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Le JwtRequestFilter étend la classe Spring Web Filter OncePerRequestFilter.
//        Pour toute demande entrante, cette classe Filter est exécutée.
//        Il vérifie si la demande a un jeton JWT valide.
//        S'il possède un jeton JWT valide,
//        il définit l'authentification en contexte pour spécifier que l'utilisateur actuel est authentifié.

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

    // Le jeton JWT se présente sous la forme «jeton porteur». Supprimer le mot porteur et obtenir
    // seulement le jeton
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);

            try {

                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {

                System.out.println("Unable to get JWT Token");

            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Une fois que nous obtenons le jeton, validez-le.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

    // si le jeton est valide, configurez Spring Security pour qu'il soit défini manuellement
    // authentification
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));



    // Après avoir défini l'authentification dans le contexte, nous spécifions
    // que l'utilisateur actuel est authentifié. Alors ça passe le
    // Configurations de sécurité Spring avec succès.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
