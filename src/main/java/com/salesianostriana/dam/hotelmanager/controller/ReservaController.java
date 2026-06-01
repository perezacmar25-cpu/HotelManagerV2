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
	 
	 
	 //este método contiene código para que un cliente no se pueda meter en las reservas de otro
	 @GetMapping("/reserva/{id}")
	 public String verDetalles(@PathVariable("id") Long id,
	                            @AuthenticationPrincipal Cliente clienteLogueado,
	                            Model model) {
	     Optional<Reserva> reservaOpt = reservaService.findById(id);

	     if (reservaOpt.isEmpty()) {
	         return "redirect:/misreservas";
	     }

	     Reserva reserva = reservaOpt.get();

	     // si no es admin y la reserva no es suya, redirigir a sus reservas
	     boolean esAdmin = clienteLogueado.getAuthorities().stream()
	             .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

	     if (!esAdmin && !reserva.getCliente().getDni().equals(clienteLogueado.getDni())) {
	         return "redirect:/misreservas";
	     }

	     model.addAttribute("reserva", reserva);
	     return "detallesreserva";
	 }
	
	 
	
	
	
	
}
