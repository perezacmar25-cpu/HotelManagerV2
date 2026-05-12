package com.salesianostriana.dam.hotelmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ReservaHabitacion {

    @Id @GeneratedValue
    private Long id;

    private String estado;

    private double costeServicios;

    private String observaciones;

    @ManyToOne 
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
    
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    
    @ManyToOne 
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
  

   

}