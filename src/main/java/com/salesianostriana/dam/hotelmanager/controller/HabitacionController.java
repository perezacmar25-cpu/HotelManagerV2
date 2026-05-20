package com.salesianostriana.dam.hotelmanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @GetMapping("/habitaciones")
    public String verDisponibilidad(Model model) {
    	
    	//Sirve para mostrar una habitacion de cada tipo en el html
        List<Habitacion> todas = habitacionService.findAll();

        List<Habitacion> unasPorTipo = new ArrayList<>();
        List<String> tiposVistos = new ArrayList<>();

        for (Habitacion h : todas) {
            if (!tiposVistos.contains(h.getTipo())) {
                tiposVistos.add(h.getTipo());
                unasPorTipo.add(h);
            }
        }

      	//Sirve para contar las disponibles
        Map<String, Long> disponibles = new HashMap<>();
        for (String tipo : tiposVistos) {
            long count = todas.stream()
                    .filter(h -> h.getTipo().equals(tipo) && h.isDisponible())
                    .count();
            disponibles.put(tipo, count);
        }

        model.addAttribute("habitaciones", unasPorTipo);
        model.addAttribute("disponibles", disponibles);
        return "habitaciones";
    }

    @GetMapping("/admin/habitaciones")
    public String listarHabitacionesAdmin(Model model) {
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "/admin/habitaciones";
    }
}