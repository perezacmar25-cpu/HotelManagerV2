package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.repository.ClienteRepository;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService extends BaseServiceImpl<Cliente, String, JpaRepository<Cliente,String>>{

	private final ClienteRepository clienteRepository;
	
	public List<Cliente> findByRolNot(RolUsuario rol) {

	    return clienteRepository.findByRolNot(rol);
	}
	
	
}
