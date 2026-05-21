package com.salesianostriana.dam.hotelmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	/*
	@Query("""
			select count
			""")
	*/
	
 
}