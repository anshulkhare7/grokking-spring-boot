package com.anshul.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry->{
                registry.requestMatchers("/home").permitAll();
                registry.requestMatchers("/admin/**").hasRole("ADMIN");
                registry.requestMatchers("/user/**").hasRole("USER");
                registry.anyRequest().authenticated();
        })
        .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
        .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
        .username("gc")
        .password("$2a$12$I8g.wX0vV8LeAJHGnH7Ld.AuJ8hJZC4MJbvTddreegBzSBGtUoUBm")
        .roles("USER")
        .build();

        UserDetails adminUser = User.builder()
        .username("admin")
        .password("$2a$12$01UBnvz2iNa.1UkpsFWPhei.PETamfJvUqIpRFuwi.zzrcPD2Osuq")
        .roles("USER", "ADMIN")
        .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}