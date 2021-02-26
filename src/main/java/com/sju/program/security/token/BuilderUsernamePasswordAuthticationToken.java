package com.sju.program.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class BuilderUsernamePasswordAuthticationToken extends UsernamePasswordAuthenticationToken {
    public BuilderUsernamePasswordAuthticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

}




