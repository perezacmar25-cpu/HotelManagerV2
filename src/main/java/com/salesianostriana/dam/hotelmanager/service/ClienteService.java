package com.salesianostriana.dam.hotelmanager.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class ClienteService extends BaseServiceImpl<Cliente, String, JpaRepository<Cliente,String>>{


	
	
}
