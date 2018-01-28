package edu.pw.platon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    public UserAuthenticationProvider(@Autowired UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (userService.login(username, password)){
            return new UsernamePasswordAuthenticationToken(username, password, userService.getAuthorities(username));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (authentication.equals(UsernamePasswordAuthenticationToken.class)) ? true : false;
    }
}
