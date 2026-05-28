package com.salesianostriana.dam.hotelmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;
    private int numeroPersonas;
    
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EstadoReserva estadoReserva = EstadoReserva.CHECKIN;
    

    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "temporada_id")
    private Temporada temporada;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<ReservaHabitacion> listadoReservaHab = new ArrayList<>();

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReservaServicio> serviciosReservados = new ArrayList<>();

    public double calcularPrecioTotal() {

        long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();

        double multiplicador;
        if (temporada != null) {
            multiplicador = temporada.getMultiplicador();
        } else {
            multiplicador = 1.0;
        }

        double totalHabitaciones = listadoReservaHab.stream()
                .mapToDouble(reshab -> reshab.getHabitacion().getPrecioNoche() * dias * multiplicador)
                .sum();
        double totalServicios = Servicio.calcularTotalServicios(serviciosReservados);

        this.precioTotal = totalHabitaciones + totalServicios;

        return precioTotal;
    }
}
