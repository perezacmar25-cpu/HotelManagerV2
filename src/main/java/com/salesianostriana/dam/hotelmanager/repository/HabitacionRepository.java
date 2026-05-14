package com.salesianostriana.dam.hotelmanager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

	@Query(value = """
		    SELECT *
		    FROM habitacion h
		    WHERE h.tipo = :tipo
		    AND h.numero NOT IN (
		        SELECT rh.habitacion_id
		        FROM reserva_habitacion rh
		        JOIN reserva r ON r.id = rh.reserva_id
		        WHERE r.fecha_inicio < :fechaFin
		          AND r.fecha_fin > :fechaInicio
		    )
		""", nativeQuery = true)
		List<Habitacion> findDisponibles(
		        @Param("tipo") String tipo,
		        @Param("fechaInicio") LocalDate fechaInicio,
		        @Param("fechaFin") LocalDate fechaFin
		);
}
