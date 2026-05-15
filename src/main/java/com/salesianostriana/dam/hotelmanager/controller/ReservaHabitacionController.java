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
import com.salesianostriana.dam.hotelmanager.model.ReservaHabitacion;
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

        
        List<Habitacion> disponibles = habitacionService.buscarDisponibles(
                tipoHabitacion,
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );

        if (disponibles.isEmpty()) {
            model.addAttribute("error", "No hay habitaciones libres");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }

        Habitacion habitacionElegida = disponibles.get(0);

        
        boolean yaReservada = reservaService.estaReservada(
                habitacionElegida.getNumero(),
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );

        if (yaReservada) {
            model.addAttribute("error", "La habitación ya está ocupada en esas fechas");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }

        
        ReservaHabitacion rH = new ReservaHabitacion();
        rH.setHabitacion(habitacionElegida);
        rH.setReserva(reserva);

        reserva.getListadoReservaHab().add(rH);

        
        Cliente clienteExistente = clienteService.findById(reserva.getCliente().getDni())
                .orElse(null);

        if (clienteExistente != null) {
            reserva.setCliente(clienteExistente);
        } else {
            clienteService.save(reserva.getCliente());
        }

        
        if (serviciosIds != null) {
            reserva.setServicios(servicioService.buscarTodosPorId(serviciosIds));
        }

        
        reserva.calcularPrecioTotal();
        boolean guardado = reservaService.guardar(reserva);

        if (!guardado) {
            model.addAttribute("error", "La habitación ya está reservada en esas fechas");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            return "formularioreserva";
        }

        return "redirect:/reserva/exito";
    }

    @GetMapping("/exito")
    public String exito() {
        return "reservaConfirmada";
    }
}