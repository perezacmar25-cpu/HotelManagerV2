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

@Entity
public class Reserva {
	
	@Id @GeneratedValue
	private Long codReserva;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private double precioTotal;
	
	private EstadoReserva estado;
	
	@ManyToOne
	@JoinColumn(name ="cliente_dni")
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "reserva")
	private List<ReservaHabitacion> listadoReservaHab = new ArrayList<>();
	
	
	
	

}
