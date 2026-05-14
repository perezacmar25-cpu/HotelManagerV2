package com.salesianostriana.dam.hotelmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Reserva {
	
	@Id @GeneratedValue
	private Long codReserva;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private double precioTotal;

	private int numeroPersonas;
	
	@ManyToOne
	@JoinColumn(name ="cliente_dni")
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "reserva")
	private List<ReservaHabitacion> listadoReservaHab = new ArrayList<>();
	
	
	
	

}
