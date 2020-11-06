package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BuilderUsernamePasswordAuthticationToekn extends UsernamePasswordAuthenticationToken {
    public BuilderUsernamePasswordAuthticationToekn(Object principal, Object credentials) {
        super(principal, credentials);
    }
}


