package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BuilderUsernamePasswordAuthticationToken extends UsernamePasswordAuthenticationToken {
    public BuilderUsernamePasswordAuthticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}


