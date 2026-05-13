package com.salesianostriana.dam.hotelmanager.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.repository.ReservaRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class ReservaService extends BaseServiceImpl<Reserva, Long, ReservaRepository> {

}