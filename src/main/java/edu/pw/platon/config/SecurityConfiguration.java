package edu.pw.platon.config;

import edu.pw.platon.user.UserAuthenticationProvider;
import edu.pw.platon.user.UserDetailsServiceImpl;
import edu.pw.platon.user.UserRepository;
import edu.pw.platon.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("edu.pw.platon.config")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService,
                                                         AuthenticationManagerBuilder authenticationManagerBuilder) {
        AuthenticationProvider authenticationProvider = new UserAuthenticationProvider(userService);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, UserService userService,
                                                 AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        UserDetailsService userDetailsService = new UserDetailsServiceImpl(userRepository, userService);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/office/*").access("hasRole('ROLE_OFFICE') or hasRole('ROLE_AUTHORITY')")
                .antMatchers("/authority/*").access("hasRole('ROLE_AUTHORITY')")
                .antMatchers("/teacher/*").access("hasRole('ROLE_TEACHER')")
                .antMatchers("/student/*").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/h2/*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/home*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(false)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/logoutScreen?logoutSuccess=true")
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2/*");
    }

}
