package com.example.agreementservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails recruiter = User.withDefaultPasswordEncoder()
            .username("recruiter")
            .password("password")
            .roles("RECRUITER")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build();

        UserDetails employer = User.withDefaultPasswordEncoder()
            .username("employer")
            .password("password")
            .roles("EMPLOYER")
            .build();

        UserDetails jobseeker = User.withDefaultPasswordEncoder()
            .username("jobseeker")
            .password("password")
            .roles("JOBSEEKER")
            .build();

        return new InMemoryUserDetailsManager(recruiter, admin, employer, jobseeker);
    }
}
