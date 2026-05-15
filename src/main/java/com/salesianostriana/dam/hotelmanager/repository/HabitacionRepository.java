package com.salesianostriana.dam.hotelmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {


	
}
