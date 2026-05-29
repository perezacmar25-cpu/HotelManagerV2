package com.salesianostriana.dam.hotelmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservaController {

	private final ReservaService reservaService;
	
	
	 @GetMapping("/misreservas")
	    public String verReservaCliente(@AuthenticationPrincipal Cliente cliente, Model model) {

	        List<Reserva> reservas = reservaService.findByClienteDni(cliente.getDni());
	        model.addAttribute("reservas", reservas);
	        model.addAttribute("nombreCliente", cliente.getNombre());
	        return "reservascliente";
	    }
	 
	 @GetMapping("/reserva/{id}")
	    public String verDetalles(@PathVariable("id") Long id, Model model) {

	        Optional<Reserva> reservaOpt = reservaService.findById(id);
	        
	        if (reservaOpt.isPresent()) {
	            model.addAttribute("reserva", reservaOpt.get());
	            return "detallesreserva"; 
	        }
	        return "redirect:/misreservas";
	    }
	
	 
	
	
	
	
}
