package com.salesianostriana.dam.hotelmanager.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final HabitacionRepository habitacionRepository;

    @GetMapping("/habitaciones")
    public String verDisponibilidad(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {

        List<Habitacion> todas = habitacionService.findAll();

        // una habitación de cada tipo para mostrar la tarjeta
        List<Habitacion> unasPorTipo = new ArrayList<>();
        List<String> tiposVistos = new ArrayList<>();
        for (Habitacion h : todas) {
            if (!tiposVistos.contains(h.getTipo())) {
                tiposVistos.add(h.getTipo());
                unasPorTipo.add(h);
            }
        }

        model.addAttribute("habitaciones", unasPorTipo);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        // solo calcular disponibilidad si se han introducido fechas
        if (fechaInicio != null && fechaFin != null && fechaFin.isAfter(fechaInicio)) {
            Map<String, Long> disponibles = new HashMap<>();
            for (String tipo : tiposVistos) {
                long count = habitacionRepository.findDisponiblesSinSolapamiento(tipo, fechaInicio, fechaFin).size();
                disponibles.put(tipo, count);
            }
            model.addAttribute("disponibles", disponibles);
        }

        return "habitaciones";
    }
}