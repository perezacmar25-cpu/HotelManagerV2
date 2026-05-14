package com.salesianostriana.dam.hotelmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabitacionService extends BaseServiceImpl<Habitacion, Integer, JpaRepository<Habitacion,Integer>>{
	
    private final HabitacionRepository habitacionRepository;

    public boolean fechasSeSolapan(LocalDate inicioExistente, LocalDate finExistente,
            LocalDate inicioNueva, LocalDate finNueva) {
        return !finNueva.isBefore(inicioExistente) && !inicioNueva.isAfter(finExistente);
    }
    
    public List<Habitacion> buscarDisponibles(String tipo, LocalDate inicio, LocalDate fin) {
        return habitacionRepository.findByTipo(tipo).stream()
            .filter(h -> h.getListadoReservas().stream()
                .noneMatch(rh -> fechasSeSolapan(
                    rh.getReserva().getFechaInicio(),
                    rh.getReserva().getFechaFin(),
                    inicio, fin
                ))
            )
            .toList();
    }
}