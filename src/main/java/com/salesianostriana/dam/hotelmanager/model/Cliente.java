package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.salesianostriana.dam.hotelmanager.security.RolUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private String dni;
	
	private String nombre;
	
	private String email;
	
	private String telefono;
	
	private String username;
	
	private String password;
	
	private RolUsuario rol;
	@OneToMany(mappedBy="cliente")
	@Builder.Default
	private List<ReservaHabitacion> listadoReservas = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+rol.name()));
		
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
	
	


