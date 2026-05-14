package com.salesianostriana.dam.hotelmanager.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("select case when count(rh) > 0 then true else false end " +
           "from ReservaHabitacion rh " +
           "where rh.habitacion.id = :habitacionId " +
           "and rh.reserva.fechaInicio < :fechaFin " +
           "and rh.reserva.fechaFin > :fechaInicio")
    boolean existeReservaParaHabitacionYFechas(
            @Param("habitacionId") int habitacionId,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("fechaInicio") LocalDate fechaInicio
    );
}