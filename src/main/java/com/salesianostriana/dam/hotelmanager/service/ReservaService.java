package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.excepciones.FechaFinInicioException;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import com.salesianostriana.dam.hotelmanager.repository.TemporadaRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends BaseServiceImpl<Reserva, Long, JpaRepository<Reserva, Long>> {

    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;
    private final TemporadaRepository temporadaRepository;

    public Optional<Reserva> crearReserva(Reserva reserva, String tipoHabitacion) {

        validarFechas(reserva);

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
        
     // Busca si la fecha de inicio de la reserva cae en alguna temporada 
     // y, si la hay, la asigna a la reserva para que calcularPrecioTotal() aplique el multiplicador correcto
        //Seteamos la temporada que devuelva la query
        temporadaRepository.findByFecha(reserva.getFechaInicio())
        .ifPresent(reserva::setTemporada);
        reserva.calcularPrecioTotal();

        return Optional.of(save(reserva));
    }
    @Override
    public Reserva save(Reserva reserva) {
        validarFechas(reserva);
        return super.save(reserva);
    }
    
    private void validarFechas(Reserva reserva) {
        if (reserva.getFechaInicio() != null &&
                reserva.getFechaFin() != null &&
                reserva.getFechaFin().isBefore(reserva.getFechaInicio())) {
            throw new FechaFinInicioException("La fecha fin no puede ser antes que la fecha de inicio");
        }
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
