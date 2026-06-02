package com.salesianostriana.dam.hotelmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	Optional<Cliente> findByUsername(String username);
	
	List<Cliente> findByRolNot(RolUsuario rol);}
