package com.sju.program.security.provider;

import com.sju.program.security.token.PoliceUsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class BuilderUsernamePasswordAuthrnticationProvider extends DaoAuthenticationProvider
    {

        @Override
        public boolean supports(Class<?> authentication) {
        return (PoliceUsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
