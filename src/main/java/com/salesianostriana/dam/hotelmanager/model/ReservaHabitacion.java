package com.salesianostriana.dam.hotelmanager.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class ReservaHabitacion {

    @Id @GeneratedValue
    private Long id;

    private EstadoReserva estado;

    private String observaciones;
    
    private boolean futbol;
    private boolean padel;
    private boolean arco;
    private boolean spa;


    @ManyToOne 
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
    
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    
    @ManyToOne 
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
  
    @ManyToMany
    @JoinTable(
        name = "reservahabitacion_servicio",
        joinColumns = @JoinColumn(name = "reservahabitacion_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    @Builder.Default
    private List<Servicio> servicios = new ArrayList<>();
}

   

