package com.api.library.model;

//Cette classe est requise pour stocker le nom d'utilisateur et le mot de passe que nous avons reçus du client.

public class JwtRequest {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    // Nécessite un constructeur par défault pour l'analyse JSON
    public JwtRequest() {
    }

    public JwtRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
