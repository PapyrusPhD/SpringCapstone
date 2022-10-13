package com.vertere.CapstoneSpring.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 5741965520462727109L;

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

}
