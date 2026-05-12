package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.hotelmanager.model.Reserva;

@Controller
public class ReservaHabitacionController {

    @GetMapping("/reserva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reserva"; 
    }

  
}