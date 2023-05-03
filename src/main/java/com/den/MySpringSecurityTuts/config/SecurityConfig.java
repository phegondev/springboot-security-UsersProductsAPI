package com.den.MySpringSecurityTuts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean /**AUTHORIZATION*/
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/product/all", "/product/{name}","/user/save").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/all","/product/save").authenticated()
                .and().formLogin()
                .and().build();
    }

//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails ADMIN = User.withUsername("den")
//                .password(passwordEncoder.encode("den1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails USER = User.withUsername("mary")
//                .password(passwordEncoder.encode("mary1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails USER1 = User.withUsername("josh")
//                .password("john1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ADMIN,USER, USER1);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
