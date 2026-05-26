package com.salesianostriana.dam.hotelmanager.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;

@Component
//Ayuda de la ia
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException {

        Collection<? extends GrantedAuthority> roles =
                authentication.getAuthorities();

        if (roles.stream().anyMatch(r ->
                r.getAuthority().equals("ROLE_ADMIN"))) {
            //el admin va al panel de administración
            response.sendRedirect("/admin/clientes");
        } else {
            //el usuario normal va al inicio
            response.sendRedirect("/");
        }
    }
}