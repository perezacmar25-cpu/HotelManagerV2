package com.salesianostriana.dam.hotelmanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.excepciones.FechaFinInicioException;
import com.salesianostriana.dam.hotelmanager.excepciones.PersonasExcedidasException;
import com.salesianostriana.dam.hotelmanager.model.EstadoReserva;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.repository.PlanComidaRepository;
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
    private final PlanComidaRepository planComidaRepository;

    public Optional<Reserva> crearReserva(Reserva reserva, String tipoHabitacion, Integer planComidaId) {

        validarFechas(reserva);
        validarPersonas(reserva.getNumeroPersonas(), tipoHabitacion);

        // Los datos calculados y las relaciones se establecen en el servidor.
        reserva.setPrecioTotal(0);
        reserva.setTemporada(null);
        reserva.setPlanComida(null);
        reserva.setListadoReservaHab(new ArrayList<>());
        reserva.setServiciosReservados(new ArrayList<>());

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
                .estado(EstadoReserva.RESERVADA)
                .build();
        reserva.getListadoReservaHab().add(reservaHabitacion);
        
        // Busca si la fecha de inicio de la reserva cae en alguna temporada 
        // y, si la hay, la asigna a la reserva para que calcularPrecioTotal() aplique el multiplicador correcto
        //Seteamos la temporada que devuelva la query
        temporadaRepository.findByFecha(reserva.getFechaInicio())
                .ifPresent(reserva::setTemporada);
        
        if (planComidaId != null) {
            planComidaRepository.findById(planComidaId).ifPresent(reserva::setPlanComida);
        }
        reserva.calcularPrecioTotal();

        Reserva reservaGuardada = reservaRepository.save(reserva);
        reservaGuardada.setCodigo(generarCodigoReserva(reservaGuardada));

        return Optional.of(reservaRepository.save(reservaGuardada));
    }

    private String generarCodigoReserva(Reserva reserva) {
        return "RES-" + reserva.getFechaInicio().getYear() + "-" + String.format("%03d", reserva.getId());
    }
    
    public void validarFechas(Reserva reserva) {
        if (reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
            throw new FechaFinInicioException("Debes indicar la fecha de entrada y la fecha de salida");
        }
        
        if (reserva.getFechaInicio().isBefore(LocalDate.now())) {
            throw new FechaFinInicioException("La fecha de entrada no puede ser anterior a hoy");
        }
        
        if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio())) {
            throw new FechaFinInicioException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
    }
    
    public void validarPersonas(Integer personas, String tipoHabitacion) {
        int maxPersonas = 0;
        
        if (personas == null || personas <= 0) {
            throw new PersonasExcedidasException("El número de personas debe ser mayor que 0");
        }

        if ("Individual".equals(tipoHabitacion)) {
            maxPersonas = 1;
        } else if ("Doble".equals(tipoHabitacion)) {
            maxPersonas = 2;
        } else if ("Suite".equals(tipoHabitacion)) {
            maxPersonas = 4;
        } else {
            throw new PersonasExcedidasException("El tipo de habitación no es válido");
        }

        if (personas > maxPersonas) {
            throw new PersonasExcedidasException("La habitación " + tipoHabitacion + " admite un máximo de " + maxPersonas + " personas.");
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
