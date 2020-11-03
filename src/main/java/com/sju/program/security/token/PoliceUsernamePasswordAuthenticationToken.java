package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PoliceUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public PoliceUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
