package com.sju.program.security.provider;

import com.sju.program.security.token.AdminUsernamePasswordAuthticationToken;
import com.sju.program.security.token.BuilderUsernamePasswordAuthticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class AdminUsernamePasswordAuthticationProvider extends DaoAuthenticationProvider
{
	@Override
	public boolean supports(Class<?> authentication) {
		return (AdminUsernamePasswordAuthticationToken.class
				.isAssignableFrom(authentication));
	}
}
