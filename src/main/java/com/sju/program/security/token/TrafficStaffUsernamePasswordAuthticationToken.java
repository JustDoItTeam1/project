package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TrafficStaffUsernamePasswordAuthticationToken extends UsernamePasswordAuthenticationToken {
    public TrafficStaffUsernamePasswordAuthticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
