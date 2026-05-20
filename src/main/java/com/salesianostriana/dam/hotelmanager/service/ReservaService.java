package com.salesianostriana.dam.hotelmanager.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.EstadoReserva;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends BaseServiceImpl<Reserva, Long, JpaRepository<Reserva, Long>> {

    private final ReservaRepository reservaRepo;
    private final HabitacionService habitacionService;

    public Optional<Reserva> crearReserva(Reserva reserva, String tipoHabitacion) {
        Optional<Habitacion> habitacion = habitacionService.findAll()
                .stream()
                .filter(h -> h.getTipo().equalsIgnoreCase(tipoHabitacion) && h.isDisponible())
                .findFirst();

        if (habitacion.isEmpty()) {
            return Optional.empty();
        }

        Habitacion habitacion2 = habitacion.get();
        habitacion2.setDisponible(false);
        habitacionService.save(habitacion2);

        ReservaHabitacion reservaHabitacion = ReservaHabitacion.builder()
                .habitacion(habitacion2)
                .reserva(reserva)
                .build();

        reserva.getListadoReservaHab().add(reservaHabitacion);


        reserva.calcularPrecioTotal();

        return Optional.of(save(reserva));
    }
}