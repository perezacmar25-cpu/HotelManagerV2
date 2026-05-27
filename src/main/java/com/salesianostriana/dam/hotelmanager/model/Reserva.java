package com.salesianostriana.dam.hotelmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    

    @ManyToOne
    @JoinColumn(name = "cliente_dni")
    private Cliente cliente;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<ReservaHabitacion> listadoReservaHab = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "reserva_servicio",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    @Builder.Default
    private List<Servicio> servicios = new ArrayList<>();

    public double calcularPrecioTotal() {

        long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();

        double totalHabitaciones = listadoReservaHab.stream()
                .mapToDouble(reshab -> reshab.getHabitacion().getPrecioNoche() * dias)
                .sum();

        double totalServicios = 0;
        
        if (!servicios.isEmpty()) {
            totalServicios = Servicio.calcularPrecioTotal(servicios);
        }

        this.precioTotal = totalHabitaciones + totalServicios;

        return precioTotal;
    }
}
