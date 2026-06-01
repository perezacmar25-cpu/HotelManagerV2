package com.salesianostriana.dam.hotelmanager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.excepciones.FechaFinInicioException;
import com.salesianostriana.dam.hotelmanager.excepciones.PersonasExcedidasException;
import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.ReservaServicio;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.repository.PlanComidaRepository;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
import com.salesianostriana.dam.hotelmanager.service.TemporadaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaHabitacionController {

    private final ReservaService reservaService;
    private final ServicioService servicioService;
    private final TemporadaService temporadaService;
    private final PlanComidaRepository planComidaRepository;
    

    private static final List<String> TIPOS = List.of("Individual", "Doble", "Suite");

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model, @AuthenticationPrincipal Cliente clienteLogueado) {

        Reserva r = new Reserva();
        r.setCliente(clienteLogueado);

        model.addAttribute("reserva", r);
        model.addAttribute("tiposHabitacion", TIPOS);
        model.addAttribute("nombreCliente", clienteLogueado.getNombre());
        model.addAttribute("temporadas", temporadaService.findAll());
        model.addAttribute("planesComida", planComidaRepository.findAll());
        model.addAttribute("fechaMinima", LocalDate.now());

        return "formularioreserva";
    }

    @PostMapping("/confirmar")
    public String procesarReserva(
    		@Valid @ModelAttribute("reserva") Reserva reserva,
            @RequestParam(required = false) String tipoHabitacion,
            BindingResult result,
            @RequestParam(required = false) Integer planComidaId,
            @AuthenticationPrincipal Cliente clienteLogueado,
            Model model) {
    	
    	if (result.hasErrors()) {
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            model.addAttribute("temporadas", temporadaService.findAll());
            model.addAttribute("planesComida", planComidaRepository.findAll());
            model.addAttribute("fechaMinima", LocalDate.now());
            return "formularioreserva";
        }

        if (reserva.getFechaInicio() == null ||
            reserva.getFechaFin() == null ||
            tipoHabitacion == null || tipoHabitacion.isBlank()) {

            model.addAttribute("error", "Debes completar todos los campos");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            model.addAttribute("temporadas", temporadaService.findAll());
            model.addAttribute("planesComida", planComidaRepository.findAll());
            model.addAttribute("fechaMinima", LocalDate.now());
            return "formularioreserva";
        }

        reserva.setCliente(clienteLogueado);

        Optional<Reserva> guardada;

        try {
            guardada = reservaService.crearReserva(reserva, tipoHabitacion, planComidaId);
        } catch (FechaFinInicioException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            model.addAttribute("temporadas", temporadaService.findAll());
            model.addAttribute("planesComida", planComidaRepository.findAll());
            model.addAttribute("fechaMinima", LocalDate.now());
            return "formularioreserva";
        }
        catch (PersonasExcedidasException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            model.addAttribute("temporadas", temporadaService.findAll());
            model.addAttribute("planesComida", planComidaRepository.findAll());
            model.addAttribute("fechaMinima", LocalDate.now());
            return "formularioreserva";
        }

        if (guardada.isEmpty()) {
            model.addAttribute("error", "No quedan habitaciones disponibles de ese tipo");
            model.addAttribute("tiposHabitacion", TIPOS);
            model.addAttribute("nombreCliente", clienteLogueado.getNombre());
            model.addAttribute("temporadas", temporadaService.findAll());
            model.addAttribute("planesComida", planComidaRepository.findAll());
            model.addAttribute("fechaMinima", LocalDate.now());
            return "formularioreserva";
        }

        return "redirect:/reserva/" + guardada.get().getId() + "/servicios";
    }

    @GetMapping("/{id}/servicios")
    public String mostrarFormularioServicios(@PathVariable Long id, Model model,
                                             @AuthenticationPrincipal Cliente clienteLogueado) {
        Optional<Reserva> reserva = reservaService.findById(id);

        // Si la reserva no existe o no pertenece al cliente logueado, redirigir
        if (reserva.isEmpty() || (!esAdmin(clienteLogueado) && !reserva.get().getCliente().getDni().equals(clienteLogueado.getDni()))) {
            return redireccionReservas(clienteLogueado);
        }

        model.addAttribute("reserva", reserva.get());
        model.addAttribute("servicios", servicioService.findAll());
        model.addAttribute("urlFinalizar", esAdmin(clienteLogueado) ? "/admin/reservas" : "/misreservas");

        return "formularioServicios";
    }

    
    //Ayuda de la ia para poder hacer el formulario de servicios según las horas que quiera el cliente
    @PostMapping("/{id}/servicios")
    public String guardarServicios(
            @PathVariable Long id,
            @RequestParam(required = false) List<Long> serviciosIds,
            HttpServletRequest request,
            @AuthenticationPrincipal Cliente clienteLogueado) {

        Optional<Reserva> reservaBuscada = reservaService.findById(id);

        if (reservaBuscada.isEmpty()) {
            return "redirect:/reserva/nueva";
        }

        
        
        Reserva reserva = reservaBuscada.get();
        
        if (!esAdmin(clienteLogueado) && !reserva.getCliente().getDni().equals(clienteLogueado.getDni())) {
            return redireccionReservas(clienteLogueado);
        }
        //Es para cuando un usuario edita los servicios de una reserva que ya tenía guardada.
        //Limpia los servicios antiguos para sustituirlos por los nuevos que acaba de elegir.
        reserva.getServiciosReservados().clear();

        if (serviciosIds != null) {
            for (Servicio servicio : servicioService.findAll()) {
                if (serviciosIds.contains(servicio.getId())) {
                	// Lee las horas del campo horas_<id> que manda el formulario
                	String horasParam = request.getParameter("horas_" + servicio.getId());

                	// Convierte las horas de texto a número.
                	// try-catch: si llega vacío o con letras, se queda en 1 hora en vez de romper la app.
                	// Math.max(1, ...): evita que alguien meta 0 o negativo manipulando el formulario.
                	int horas = 1;
                	try {
                	    horas = Math.max(1, Integer.parseInt(horasParam));
                	} catch (Exception ignored) {
                	}
                    
                    
                    ReservaServicio reservaServicio = ReservaServicio.builder()
                            .reserva(reserva)
                            .servicio(servicio)
                            .cantidad(horas)
                            .precioUnidad(servicio.getPrecio())
                            .build();

                    reserva.getServiciosReservados().add(reservaServicio);
                }
            }
        }

        reserva.calcularPrecioTotal();
        reservaService.save(reserva);

        return redireccionReservas(clienteLogueado);
    }

    private boolean esAdmin(Cliente cliente) {
        return cliente.getRol() == RolUsuario.ADMIN;
    }

    private String redireccionReservas(Cliente cliente) {
        return esAdmin(cliente) ? "redirect:/admin/reservas" : "redirect:/misreservas";
    }
}
