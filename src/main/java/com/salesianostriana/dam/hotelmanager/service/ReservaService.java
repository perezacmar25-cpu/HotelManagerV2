package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends BaseServiceImpl<Reserva, Long, JpaRepository<Reserva, Long>> {

    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;;

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
    public List<Reserva> findByClienteDni(String dni) {
        return reservaRepository.findByClienteDni(dni);
    }
    
    //elimina las reservas que están asociadas a la habitación que queremos borrar
    public void eliminarPorHabitacion(int numeroHabitacion) {
        List<Reserva> reservas = reservaRepository.findByHabitacionNumero(numeroHabitacion);
        reservaRepository.deleteAll(reservas);
    }
}