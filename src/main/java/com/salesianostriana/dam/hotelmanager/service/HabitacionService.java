package com.salesianostriana.dam.hotelmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class HabitacionService extends BaseServiceImpl<Habitacion, Integer, JpaRepository<Habitacion,Integer>>{
	
	  @Autowired
    private HabitacionRepository habitacionRepository;

    //Método consultado de la ia
    public List<Habitacion> encontrarPorTipo() {
        return habitacionRepository.findAll()
            .stream()
            .collect(Collectors.toMap(
                Habitacion::getTipo,
                h -> h,
                (existing, replacement) -> existing
            ))
            .values()
            .stream()
            .toList();
    }
    
    public List<Object[]> getHabitacionesMasUsadas() {
        
        List<Object[]> todas = habitacionRepository.encontrarHabMasUsadas();
        
        // Si la lista tiene más de 3 elementos, la cortamos para quedarnos solo con los 3 primeros
        if (todas.size() > 3) {
            return todas.subList(0, 3);
        }
        return todas;
    }
    
    
  
    
   
}