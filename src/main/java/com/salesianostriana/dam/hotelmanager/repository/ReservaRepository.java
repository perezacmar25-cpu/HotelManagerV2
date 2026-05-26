package com.salesianostriana.dam.hotelmanager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByClienteDni(String dni);
	
	@Query("""
			SELECT r FROM Reserva r JOIN r.listadoReservaHab rh
			WHERE rh.habitacion.numero = :numero
			""")
	List<Reserva> findByHabitacionNumero(@Param("numero") int numero);
	
 
}