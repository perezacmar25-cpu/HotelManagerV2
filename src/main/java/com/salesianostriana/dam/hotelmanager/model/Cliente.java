package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
	


	public Cliente(String dni, String nombre, String email, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	@Id 
	private String dni;
	
	private String nombre;
	
	private String email;
	
	private String telefono;
	
	@OneToMany(mappedBy="cliente")
	private List<ReservaHabitacion> listadoReservas = new ArrayList<>();
	
	

}
