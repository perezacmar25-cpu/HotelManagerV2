package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends BaseServiceImpl<Reserva, Long, JpaRepository<Reserva, Long>> {

    private final HabitacionRepository habitacionRepository;

    public Optional<Reserva> crearReserva(Reserva reserva, String tipoHabitacion) {

        List<Habitacion> disponibles = habitacionRepository.findDisponiblesSinSolapamiento(
                tipoHabitacion,
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );


        if (disponibles.isEmpty()) {
            return Optional.empty();
        }

        Habitacion habitacion = disponibles.get(0);



        //así se hace con builder
        ReservaHabitacion reservaHabitacion = ReservaHabitacion.builder()
                .habitacion(habitacion)
                .reserva(reserva)
                .build();

        reserva.getListadoReservaHab().add(reservaHabitacion);
        reserva.calcularPrecioTotal();

        return Optional.of(save(reserva));
    }
}