package com.salesianostriana.dam.hotelmanager.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PlanComida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private PlanComidaEnum tipo;
	
	private int numeroDias;
	
	private double precio;
	
    public double calcularTotalComida() {
        return precio * numeroDias;
    }
	

}
