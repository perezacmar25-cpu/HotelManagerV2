package com.salesianostriana.dam.hotelmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ReservaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Servicio servicio;

    private int cantidad;

    private double precioUnidad;

    public double calcularSubtotal() {
        return cantidad * precioUnidad;
    }
}
