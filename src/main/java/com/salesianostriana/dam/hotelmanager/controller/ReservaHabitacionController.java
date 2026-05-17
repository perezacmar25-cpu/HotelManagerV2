package com.salesianostriana.dam.hotelmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaHabitacionController {

    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final ServicioService servicioService;


    private static final List<String> TIPOS = List.of("Individual", "Doble", "Suite");

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        Reserva r = new Reserva();
        r.setCliente(new Cliente());

        model.addAttribute("reserva", r);
        model.addAttribute("tiposHabitacion", TIPOS);
        model.addAttribute("servicios", servicioService.findAll());


        return "formularioreserva";
        
        
    }

    @PostMapping("/confirmar")
    public String procesarReserva(
            @ModelAttribute("reserva") Reserva reserva,
            @RequestParam(required = false) List<Long> serviciosIds,
            @RequestParam(required = false) String tipoHabitacion,
            Model model) {

        
        if (reserva.getFechaInicio() == null ||
            reserva.getFechaFin() == null ||
            tipoHabitacion == null || tipoHabitacion.isBlank() ||
            reserva.getCliente() == null ||
            reserva.getCliente().getDni() == null) {

            model.addAttribute("error", "Debes completar todos los campos");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }
        Cliente clienteExistente = clienteService.findById(reserva.getCliente().getDni())
                .orElse(null);

        if (clienteExistente != null) {
            reserva.setCliente(clienteExistente);
        } else {
            clienteService.save(reserva.getCliente());
        }

        
        if (serviciosIds != null) {
            reserva.setServicios(servicioService.findAll());
        }
        reserva.calcularPrecioTotal();
        Reserva guardada = reservaService.save(reserva);

        if (guardada == null) {
            model.addAttribute("error", "No se pudo guardar la reserva");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }

        return "redirect:/reserva/exito";
    }
    
    @GetMapping("/editar/{id}")
    public String modificarReserva(@PathVariable("id") Long id, Model model) {
    	
    	Optional<Reserva> reserva = reservaService.findById(id);
    	
    	if(reserva.isPresent()) {
    		model.addAttribute("reserva", reserva.get());
    		return "admin/formularioreserva";
    	}else {
    		return "admin/reservas";
    	}
    	
    }
    
    public String borrarReserva(@PathVariable("id") Long id, Model model) {
    	
    	Optional <Reserva> reserva = reservaService.findById(id);

		if (reserva.isPresent()) {
			reservaService.delete(reserva.get());
		}
		return "redirect:/admin/reservas/";
    	
    }
    
    public String listarReservas(Model model) {
    	model.addAttribute("reservas", reservaService.findAll());
    	return "reservas";
    }
    
    @GetMapping("/formreserva")
    public String redirigirReserva() {
        return "formularioreserva";
    }

    @GetMapping("/exito")
    public String exito() {
        return "reservaConfirmada";
    }
}
