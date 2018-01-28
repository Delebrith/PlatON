package edu.pw.platon.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserService {
    boolean login(String username, String password);
    List<GrantedAuthority> getAuthorities(String username);
}
