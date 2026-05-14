package com.salesianostriana.dam.hotelmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
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

    //Le he preguntado a la ia:
    private static final List<String> TIPOS =
            List.of("Individual", "Doble", "Suite");

    @GetMapping("/nueva")
    public String crearReservaForm(Model model) {

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

        List<Habitacion> disponibles = habitacionService.findDisponibles(
                tipoHabitacion,
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );

        if (disponibles.isEmpty()) {

            model.addAttribute("error", "No hay habitaciones disponibles");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }

        Cliente cliente = clienteService.findById(reserva.getCliente().getDni())
                .orElse(null);

        if (cliente != null) {
            reserva.setCliente(cliente);
        } else {
            clienteService.save(reserva.getCliente());
        }

        if (serviciosIds != null) {
            reserva.setServicios(servicioService.findAllById(serviciosIds));
        }
        
        reserva.calcularPrecioTotal();

        reservaService.save(reserva);

        return "redirect:/reserva/exito";
    }

    @GetMapping("/exito")
    public String exito() {
        return "reservaConfirmada";
    }
}