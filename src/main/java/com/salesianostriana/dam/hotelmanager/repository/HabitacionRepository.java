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

	 @Query("""
		        SELECT h FROM Habitacion h
		        WHERE h.tipo = :tipo
		          AND h.numero NOT IN (
		              SELECT rh.habitacion.numero
		              FROM ReservaHabitacion rh
		              WHERE rh.reserva.fechaInicio < :fechaFin
		                AND rh.reserva.fechaFin   > :fechaInicio
		          )
		        """)
		    List<Habitacion> findDisponiblesSinSolapamiento(
		            @Param("tipo")        String    tipo,
		            @Param("fechaInicio") LocalDate fechaInicio,
		            @Param("fechaFin")    LocalDate fechaFin
		    );
	 
	 @Query("""
	            SELECT h FROM Habitacion h
	            WHERE h.numero IN (
	                SELECT rh.habitacion.numero
	                FROM ReservaHabitacion rh
	                WHERE rh.reserva.fechaInicio <= :hoy
	                  AND rh.reserva.fechaFin    >  :hoy
	            )
	            """)
	    List<Habitacion> findOcupadasHoy(@Param("hoy") LocalDate hoy);
	 
	 
	 @Query("""
	 		SELECT h.numero as numero, h.tipo as tipo, COUNT(rh) as vecesReservada
    FROM Habitacion h 
    LEFT JOIN ReservaHabitacion rh ON rh.habitacion.numero = h.numero
    GROUP BY h.numero, h.tipo
    ORDER BY vecesReservada DESC
	 		""")
	 List<Object[]>encontrarHabMasUsadas();
	
}
