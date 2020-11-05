package com.sju.program.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TrafficStaffUsernamePasswordAuthticationToekn extends UsernamePasswordAuthenticationToken {
    public TrafficStaffUsernamePasswordAuthticationToekn(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
