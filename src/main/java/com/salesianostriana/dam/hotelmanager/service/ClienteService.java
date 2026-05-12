package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
	
	
}
