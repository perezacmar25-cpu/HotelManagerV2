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
	
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private PlanComidaEnum tipo;
	
	private int numeroDias;
	
	
	public double getPrecioDiario() {
        if (tipo == null) {
        	return 0.0;
        }
        switch (tipo) {
            case SOLO_DESAYUNO: 
            	return 10.0;   
            case MEDIA_PENSION:
            	return 25.0;   
            case PENSION_COMPLETA:
            	return 45.0;
            case TODO_INCLUIDO:
            	return 70.0;   
            default: 
            	return 0.0;
        }
    }
    public double calcularTotalComida() {

        return getPrecioDiario() * numeroDias;
    }
	

}
