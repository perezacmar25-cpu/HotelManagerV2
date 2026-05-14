package com.salesianostriana.dam.hotelmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaHabitacionController {

    private final HabitacionService habitacionService; 

    private final ReservaService reservaService;
    
    private final ClienteService clienteService;
    
    private final ServicioService servicioService;

    @GetMapping("/nueva")
    public String crearReservaForm(Model model) {
        Reserva r = new Reserva();
        r.setCliente(new Cliente()); 
        model.addAttribute("reserva", r);
        model.addAttribute("habitaciones", habitacionService.findAll());
        model.addAttribute("servicios", servicioService.findAll());
        return "formularioreserva"; 
    }


    @PostMapping("/confirmar")
    public String procesarReserva(
            @ModelAttribute("reserva") Reserva reserva,
            @RequestParam(required = false) List<Long> serviciosIds,
            @RequestParam String tipoHabitacion) { 
    	
        Habitacion habitacion = habitacionService.findAll().stream()
                .filter(h -> h.getTipo().equals(tipoHabitacion))
                .findFirst()
                .orElse(null);


        String dni = reserva.getCliente().getDni();
        Cliente clienteExistente = clienteService.findById(dni).orElse(null);
        if (clienteExistente != null) {
            reserva.setCliente(clienteExistente);
        } else {
            clienteService.save(reserva.getCliente());
        }


        if (serviciosIds != null) {
            List<Servicio> serviciosSeleccionados = servicioService.findAllById(serviciosIds);
            reserva.setServicios(serviciosSeleccionados);
        }

        reservaService.save(reserva);
        return "redirect:/reserva/exito"; 
    }

    

    @GetMapping("/exito")
    public String pantallaExito() {
        return "reservaConfirmada"; 
    }
}