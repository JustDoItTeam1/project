package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AdminUsernamePasswordAuthticationToken extends UsernamePasswordAuthenticationToken {
    public AdminUsernamePasswordAuthticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

}




