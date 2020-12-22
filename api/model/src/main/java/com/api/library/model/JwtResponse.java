package com.api.library.model;

//Cette classe est requise pour créer une réponse contenant le JWT à renvoyer à l'utilisateur.

public class JwtResponse {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(final String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return jwttoken;
    }
}
