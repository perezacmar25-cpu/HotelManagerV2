package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaHabitacionController {

    private final HabitacionService habitacionService; 

    private final ReservaService reservaService;
    
    private final ClienteService clienteService;

    @GetMapping("/nueva")
    public String crearReservaForm(Model model) {
        Reserva r = new Reserva();
        r.setCliente(new Cliente()); 
        model.addAttribute("reserva", r);
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "formularioreserva"; 
    }


    @PostMapping("/confirmar")
    public String procesarReserva(@ModelAttribute("reserva") Reserva reserva) {
        String dni = reserva.getCliente().getDni();
        Cliente clienteExistente = clienteService.findById(dni).orElse(null);

        if (clienteExistente != null) {
            reserva.setCliente(clienteExistente);
        } else {
            clienteService.save(reserva.getCliente());
        }
        reservaService.save(reserva);
        return "redirect:/reserva/exito"; 
    }

    @GetMapping("/exito")
    public String pantallaExito() {
        return "reservaConfirmada"; 
    }
}