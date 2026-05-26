package com.salesianostriana.dam.hotelmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	Optional<Cliente> findByUsername(String username);
}
