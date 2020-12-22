package com.web.library.weblibrary.beans;

public class AuthenticationCustomer {

    private String username;
    private String password;

    @Override
    public String toString() {
        return "AuthenticationCustomer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
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
