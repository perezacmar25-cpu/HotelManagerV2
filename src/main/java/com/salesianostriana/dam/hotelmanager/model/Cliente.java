package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.List;

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

public class Cliente {
	



	@Id 
	private String dni;
	
	private String nombre;
	
	private String email;
	
	private String telefono;
	
	@OneToMany(mappedBy="cliente")
	@Builder.Default
	private List<ReservaHabitacion> listadoReservas = new ArrayList<>();
	
	

}
