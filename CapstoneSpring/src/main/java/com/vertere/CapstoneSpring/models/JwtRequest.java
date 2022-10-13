package com.vertere.CapstoneSpring.models;

import java.io.Serializable;

public class JwtRequest implements Serializable {
//    model to be used in creating a JWT using the request body contents for the payload in the "AuthController" file.

    private static final long serialVersionUID = 4045588897201282258L;

    private String username;
    private String password;

    public JwtRequest() {}

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
