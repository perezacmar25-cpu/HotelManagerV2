	package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservaController {

	private final ReservaService reservaService;
	
	   @GetMapping("/editar/{id}")
	    public String editarReservaForm(@PathVariable Long id, Model model) {
	        reservaService.findById(id).ifPresent(r -> model.addAttribute("reserva", r));
	        return "editarReserva";
	    }
	   
	   @PostMapping("/editar/{id}")
	    public String editarReserva(@PathVariable Long id, @ModelAttribute Reserva reserva) {
	        reservaService.save(reserva);
	        return "redirect:/reservas";
	    }
	
	   @GetMapping("/{id}/eliminar")
	   public String eliminarReserva(@PathVariable Long id) {
		   reservaService.deleteById(id);
		   return "redirect:/reservas";
	   }
	   
	   
	   @GetMapping("/reservas")
	public String listarReservas(Model model) {
		model.addAttribute("reservas", reservaService.findAll());
		return "admin/reservas";
	}
	
	
	
	
}
