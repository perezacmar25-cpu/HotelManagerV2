package com.salesianostriana.dam.hotelmanager.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends BaseServiceImpl<Reserva, Long, JpaRepository<Reserva,Long>>{

    private final ReservaRepository reservaRepo;

   
}