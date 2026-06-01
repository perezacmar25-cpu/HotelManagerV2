package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.salesianostriana.dam.hotelmanager.security.RolUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class Cliente implements UserDetails{
	
	@Id
	@NotBlank(message = "El DNI es obligatorio")
	@Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI debe tener 8 numeros y una letra")
	private String dni;

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@Email(message = "El email no es válido")
	private String email;

	@Pattern(regexp = "^[6789]\\d{8}$", message = "El teléfono debe tener 9 dígitos y empezar por 6, 7, 8 o 9")
	private String telefono;

	@NotBlank(message = "El usuario es obligatorio")
	private String username;

	
	private String password;
	
	private RolUsuario rol;
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Reserva> reservas = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    if (rol == null) return List.of();
	    return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
	}
	
	 @Override
	    public boolean isAccountNonExpired() { 
		 return true;
		 }
	 
	    @Override
	    public boolean isAccountNonLocked() {
	    	return true;
	    	}
	 
	    @Override
	    public boolean isCredentialsNonExpired() {
	    	return true; 
	    }
	 
	    @Override
	    public boolean isEnabled() {
	    	return true; 
	    	}
	}
	
	


