package com.sju.program.security.provider;

import com.sju.program.security.token.PoliceUsernamePasswordAuthenticationToken;
import com.sju.program.security.token.TrafficStaffUsernamePasswordAuthticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class TrafficStaffUsernamePasswordAuthrnticationProvider extends DaoAuthenticationProvider {

    @Override
    public boolean supports(Class<?> authentication) {
        return (TrafficStaffUsernamePasswordAuthticationToken.class
                .isAssignableFrom(authentication));
    }
}
