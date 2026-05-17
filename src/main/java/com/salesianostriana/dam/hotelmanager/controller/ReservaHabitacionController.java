package com.salesianostriana.dam.hotelmanager.controller;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
 
import lombok.RequiredArgsConstructor;
 
@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaHabitacionController {
 
    private final ReservaService reservaService;
    private final ServicioService servicioService;
 
    private static final List<String> TIPOS = List.of("Individual", "Doble", "Suite");
 
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model, @AuthenticationPrincipal Cliente clienteLogueado) {
 
        Reserva r = new Reserva();
        // Usamos directamente el cliente que ha iniciado sesión
        r.setCliente(clienteLogueado);
 
        model.addAttribute("reserva", r);
        model.addAttribute("tiposHabitacion", TIPOS);
        model.addAttribute("servicios", servicioService.findAll());
        // Pasamos el nombre del cliente al formulario para mostrarlo
        model.addAttribute("nombreCliente", clienteLogueado.getNombre());
 
        return "formularioreserva";
    }
 
    @PostMapping("/confirmar")
    public String procesarReserva(
            @ModelAttribute("reserva") Reserva reserva,
            @RequestParam(required = false) List<Long> serviciosIds,
            @RequestParam(required = false) String tipoHabitacion,
            @AuthenticationPrincipal Cliente clienteLogueado,
            Model model) {
 
        // Validación de campos obligatorios
        if (reserva.getFechaInicio() == null ||
            reserva.getFechaFin() == null ||
            tipoHabitacion == null || tipoHabitacion.isBlank()) {
 
            model.addAttribute("error", "Debes completar todos los campos");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            return "formularioreserva";
        }
 
        // Asignamos el cliente logueado a la reserva (no hace falta buscarlo ni crearlo)
        reserva.setCliente(clienteLogueado);
 
        if (serviciosIds != null) {
            reserva.setServicios(servicioService.findAll());
        }
 
        reserva.calcularPrecioTotal();
        Reserva guardada = reservaService.save(reserva);
 
        if (guardada == null) {
            model.addAttribute("error", "No se pudo guardar la reserva");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("servicios", servicioService.findAll());
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            return "formularioreserva";
        }
 
        return "redirect:/reserva/exito";
    }
 
    @GetMapping("/editar/{id}")
    public String modificarReserva(@PathVariable("id") Long id, Model model) {
        Optional<Reserva> reserva = reservaService.findById(id);
        if (reserva.isPresent()) {
            model.addAttribute("reserva", reserva.get());
            return "admin/formularioreserva";
        } else {
            return "admin/reservas";
        }
    }
 
    @GetMapping("/exito")
    public String exito() {
        return "reservaConfirmada";
    }
}