package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Habitacion {

	@Id @GeneratedValue
	private int numero;
	
	private String tipo;
	
	private double precioNoche;
	
	
	@OneToMany(mappedBy = "habitacion")
	private List<ReservaHabitacion> listadoReservas = new ArrayList<>();
	
	
	
}

