package edu.pw.platon.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        String password = user.getPassword();
        Collection<GrantedAuthority> authorities = userService.getAuthorities(username);
        return new org.springframework.security.core.userdetails.User(
                username, password, authorities);
    }
}
