package com.salesianostriana.dam.hotelmanager.security;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import lombok.RequiredArgsConstructor;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 
        http.authorizeHttpRequests(authz -> authz
              
        		// Páginas públicas: cualquiera puede entrar sin login 																																		
                .requestMatchers("/", "/index", "/login", "/registro", "/nuevo",
                                 "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()
        )
        .requestCache(cache -> {
            HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
            requestCache.setMatchingRequestParameterName(null);
            cache.requestCache(requestCache);
        })
        .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login?error")                
                .permitAll()
        );
 
        
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(opts -> opts.disable()));
 
        return http.build();
    }
}
 