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

   
}