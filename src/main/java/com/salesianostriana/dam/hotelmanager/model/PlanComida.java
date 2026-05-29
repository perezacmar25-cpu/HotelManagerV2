package com.salesianostriana.dam.hotelmanager.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;

@Entity
public class PlanComida {
	
	@Id @GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private PlanComidaEnum tipo;
	
	private int numeroDias;
	
	
	public double calcularTotalComida() {
		
		
	}
	

}
