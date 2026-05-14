package com.salesianostriana.dam.hotelmanager.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepo;

    public boolean guardar(Reserva r) {
        for (ReservaHabitacion rh : r.getListadoReservaHab()) {
            boolean ocupada = reservaRepo.existeReservaParaHabitacionYFechas(
                rh.getHabitacion().getNumero(),
                r.getFechaFin(),
                r.getFechaInicio()
            );
            if (ocupada) {
                return false;
            }
        }
        reservaRepo.save(r);
        return true;
    }

    public boolean estaReservada(int habitacionId, LocalDate inicio, LocalDate fin) {
        return reservaRepo.existeReservaParaHabitacionYFechas(habitacionId, fin, inicio);
    }
}