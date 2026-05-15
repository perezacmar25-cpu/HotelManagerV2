package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;

@Controller
public class InicioController {

    @Autowired
    private HabitacionService habitacionService; 
    @Autowired	
    private ServicioService servicioService;
    
    @GetMapping("/")
    public String init(Model model) {
 
        model.addAttribute("habitaciones", habitacionService.findAll());
        model.addAttribute("servicios", servicioService.findAll());
        return "inicio"; 
    }

    @GetMapping("/login")
    public String formularioRegistro() {
        return "index"; 
    }
    
    
}
