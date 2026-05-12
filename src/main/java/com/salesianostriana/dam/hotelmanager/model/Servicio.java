package com.salesianostriana.dam.hotelmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Servicio {
	
	@Id @GeneratedValue
	private long id;
	
	private String nombre;
	
	private double precio;
	
	
	

}
