package com.salesianostriana.dam.hotelmanager.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.hotelmanager.model.Temporada;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
	@Query("""
	        SELECT t FROM Temporada t
	        WHERE :fecha BETWEEN t.fechaInicio AND t.fechaFin
	        """)
	Optional<Temporada> findByFecha(@Param("fecha") LocalDate fecha);
}