package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.repository.ServicioRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class ServicioService extends BaseServiceImpl<Servicio, Long, ServicioRepository> {


    public List<Servicio> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
