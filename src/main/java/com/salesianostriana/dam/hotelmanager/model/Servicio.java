package com.salesianostriana.dam.hotelmanager.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Servicio {
	
	@Id @GeneratedValue
	private long id;
	
	private String nombre;
	
	private double precio;
	
    private String imagen;
    
    public static double calcularTotalServicios(List<ReservaServicio> servicios) {
        if (servicios.isEmpty()) return 0;
        return servicios.stream()
                .mapToDouble(ReservaServicio::calcularSubtotal)
                .sum();
    }
	

}
