package com.salesianostriana.dam.hotelmanager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
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
	private final HabitacionRepository habitacionRepository;
	
	
	@GetMapping("/admin/admininicio")
	public String panelAdmin() {
		return "/admin/admininicio";
	}
	
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
	       reservaService.eliminarPorHabitacion(id);   // borra las reservas asociadas
	       habitacionService.deleteById(id);            // borra la habitación
	       return "redirect:/admin/habitaciones";
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
	   
	   @GetMapping("/admin/agregar/habitacion")
	   public String agregarHabitacion(@ModelAttribute Habitacion h) {
		   if (h.getTipo().equals("Individual")) {
			   h.setPrecioNoche(55.0);
		   } else if (h.getTipo().equals("Doble")) {
			   h.setPrecioNoche(100.0);
		   } else if (h.getTipo().equals("Suite")) {
			   h.setPrecioNoche(155.0);
		   }
	       habitacionService.save(h);
	       return "redirect:/admin/habitaciones";
	   }
	   
	   
		@GetMapping("/admin/habitaciones")
		public String listarHabitaciones(Model model) {
	 
			LocalDate hoy = LocalDate.now();
			Set<Integer> ocupadasHoy = habitacionRepository.findOcupadasHoy(hoy)
					.stream()
					.map(Habitacion::getNumero)
					.collect(Collectors.toSet());
	 
			List<Habitacion> todas = habitacionService.findAll();
			todas.forEach(h -> h.setDisponible(!ocupadasHoy.contains(h.getNumero())));
			todas.sort((h1, h2) -> Integer.compare(ordenTipo(h1.getTipo()), ordenTipo(h2.getTipo())));
	 
			model.addAttribute("habitaciones", todas);
			return "/admin/habitaciones";
		}

		// ordenarlas para que se muestre bien la lista
		private int ordenTipo(String tipo) {
			if (tipo.equals("Individual")) {
				return 1;
			} else if (tipo.equals("Doble")) {
				return 2;
			} else if (tipo.equals("Suite")) {
				return 3;
			}
			return 4;
		}
	   
	   
	   
	
}
