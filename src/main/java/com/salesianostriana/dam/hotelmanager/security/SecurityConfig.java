package com.salesianostriana.dam.hotelmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/reserva/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
            		.loginPage("/login")
            		.defaultSuccessUrl("/reserva/nueva",true)
            		.permitAll()
            		)
         
            	.logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}