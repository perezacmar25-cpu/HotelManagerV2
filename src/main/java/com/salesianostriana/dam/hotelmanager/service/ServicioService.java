package com.salesianostriana.dam.hotelmanager.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class ServicioService extends BaseServiceImpl<Servicio, Long, JpaRepository<Servicio,Long>>{

}
