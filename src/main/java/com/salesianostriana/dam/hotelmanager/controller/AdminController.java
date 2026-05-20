package com.salesianostriana.dam.hotelmanager.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final ReservaService reservaService;
	private final ClienteService clienteService;
	private final HabitacionService habitacionService;
	
	@GetMapping("/admin/editar/reserva/{id}")
	public String editarReservaForm(@PathVariable Long id, Model model) {
	    Optional<Reserva> reserva = reservaService.findById(id);
	    model.addAttribute("reserva", reserva.get());
	    if (reserva.isEmpty()) {
	    	return "redirect:/admin/reservas";
	    }
	    return "formularioReserva";
	}
	   
	   @PostMapping("/admin/editar/reserva/{id}")
	   public String editarReserva(@PathVariable Long id, @ModelAttribute Reserva reserva) {
	       reserva.setId(id);
	       reservaService.save(reserva);
	       return "redirect:/admin/reservas";
	   }
	
	   @GetMapping("/admin/{id}/eliminar/reserva")
	   public String eliminarReserva(@PathVariable Long id) {
		   reservaService.deleteById(id);
		   return "redirect:/admin/reservas";
	   }
	   
	   @GetMapping("/admin/reservas")
	public String listarReservas(Model model) {
		model.addAttribute("reservas", reservaService.findAll());
		return "/admin/reservas";
	}
	   
	   @GetMapping("/admin/{id}/eliminar/habitaciones")
	   public String borrarHabitacion(@PathVariable int id) {
			    habitacionService.deleteById(id);
			    return "redirect:/admin/habitaciones";
		   
	   }
	   
	   @GetMapping("/admin/habitaciones")
	   public String listarHabitaciones(Model model) {
		   model.addAttribute("habitaciones",habitacionService.findAll());
		   return "/admin/habitaciones";
	   }
	   
	   
	   @GetMapping("/admin/clientes")
	    public String listarClientes(Model model) {
	        model.addAttribute("clientes", clienteService.findAll());
	        return "/admin/clientes";
	    }
	   
	   @GetMapping("/admin/{dni}/eliminar/cliente")
	   public String eliminarCliente(@PathVariable String dni) {
		   clienteService.deleteById(dni);
		   return "redirect:/admin/clientes";
	   }
	   
	   
	   
	
}
