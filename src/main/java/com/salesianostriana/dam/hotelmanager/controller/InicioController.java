package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InicioController {

   
    private final HabitacionService habitacionService; 
 
    private final ServicioService servicioService;
    
    @GetMapping("/")
    public String init(Model model) {
 
        model.addAttribute("habitaciones", habitacionService.encontrarPorTipo());
        model.addAttribute("servicios", servicioService.findAll());
        return "inicio"; 
    }

    @GetMapping("/login")
    public String formularioRegistro() {
        return "formlogin"; 
    }
    
    
}
