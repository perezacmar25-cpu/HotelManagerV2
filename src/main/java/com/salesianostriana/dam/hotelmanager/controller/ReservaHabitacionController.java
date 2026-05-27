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

import com.salesianostriana.dam.hotelmanager.excepciones.FechaFinInicioException;
import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaServicio;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
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
        r.setCliente(clienteLogueado);

        model.addAttribute("reserva", r);
        model.addAttribute("tiposHabitacion", TIPOS);
        model.addAttribute("nombreCliente", clienteLogueado.getNombre());


        return "formularioreserva";
    }

    @PostMapping("/confirmar")
    public String procesarReserva(
            @ModelAttribute("reserva") Reserva reserva,
            @RequestParam(required = false) String tipoHabitacion,
            @AuthenticationPrincipal Cliente clienteLogueado,
            Model model) {

        if (reserva.getFechaInicio() == null ||
            reserva.getFechaFin() == null ||
            tipoHabitacion == null || tipoHabitacion.isBlank()) {

            model.addAttribute("error", "Debes completar todos los campos");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            return "formularioreserva";
        }

        reserva.setCliente(clienteLogueado);

        Optional<Reserva> guardada;

        try {
            guardada = reservaService.crearReserva(reserva, tipoHabitacion);
        } catch (FechaFinInicioException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            return "formularioreserva";
        }

        if (guardada.isEmpty()) {
            model.addAttribute("error", "No quedan habitaciones disponibles de ese tipo");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            return "formularioreserva";
        }

        return "redirect:/reserva/" + guardada.get().getId() + "/servicios";
    }

    @GetMapping("/{id}/servicios")
    public String mostrarFormularioServicios(@PathVariable Long id, Model model) {
        Optional<Reserva> reserva = reservaService.findById(id);

        if (reserva.isEmpty()) {
            return "redirect:/reserva/nueva";
        }

        model.addAttribute("reserva", reserva.get());
        model.addAttribute("servicios", servicioService.findAll());

        return "formularioServicios";
    }

    @PostMapping("/{id}/servicios")
    public String guardarServicios(
            @PathVariable Long id,
            @RequestParam(required = false) List<Long> serviciosIds) {

        Optional<Reserva> reservaBuscada = reservaService.findById(id);

        if (reservaBuscada.isEmpty()) {
            return "redirect:/reserva/nueva";
        }

        Reserva reserva = reservaBuscada.get();
        reserva.getServiciosReservados().clear();

        if (serviciosIds != null) {
            for (Servicio servicio : servicioService.findAll()) {
                if (serviciosIds.contains(servicio.getId())) {
                    ReservaServicio reservaServicio = ReservaServicio.builder()
                            .reserva(reserva)
                            .servicio(servicio)
                            .cantidad(1)
                            .precioUnidad(servicio.getPrecio())
                            .build();

                    reserva.getServiciosReservados().add(reservaServicio);
                }
            }
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
