package com.salesianostriana.dam.hotelmanager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.excepciones.BorrarAdminException;
import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.EstadoReserva;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.PlanComida;
import com.salesianostriana.dam.hotelmanager.model.PlanComidaEnum;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.repository.HabitacionRepository;
import com.salesianostriana.dam.hotelmanager.repository.PlanComidaRepository;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.PlanComidaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
import com.salesianostriana.dam.hotelmanager.service.TemporadaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final ReservaService reservaService;
	private final ClienteService clienteService;
	private final HabitacionService habitacionService;
	private final HabitacionRepository habitacionRepository;
	private final TemporadaService temporadaService;
	private final PlanComidaRepository planComidaRepository;
	private final PlanComidaService planComidaService;
	private final ServicioService servicioService;
	
	
	@GetMapping("/admin/admininicio")
	public String panelAdmin(Model model) {
		LocalDate hoy = LocalDate.now();
		List<Reserva> llegadasHoy = reservaService.findAll().stream()
				.filter(r -> r.getFechaInicio() != null)
				.filter(r -> r.getFechaInicio().equals(hoy))
				.filter(r -> r.getEstadoReserva() == EstadoReserva.RESERVADA)
				.collect(Collectors.toList());
		
		model.addAttribute("llegadasHoy", llegadasHoy);
		model.addAttribute("hoy", hoy);
		return "/admin/admininicio";
	}
	
	@GetMapping("/admin/editar/reserva/{id}")
	public String editarReservaForm(@PathVariable Long id, Model model) {
	    Optional<Reserva> reserva = reservaService.findById(id);
	    if (reserva.isEmpty()) {
	    	return "redirect:/admin/reservas";
	    }
	    Reserva reservaEditar = reserva.get();
	    model.addAttribute("reserva", reservaEditar);
	    model.addAttribute("tiposHabitacion", List.of("Individual", "Doble", "Suite"));
	    model.addAttribute("temporadas", temporadaService.findAll());
	    model.addAttribute("planesComida", planComidaRepository.findAll());
	    model.addAttribute("fechaMinima", LocalDate.now());
	    model.addAttribute("nombreCliente", reservaEditar.getCliente().getNombre());
	    model.addAttribute("editandoReserva", true);
	    
	    if (!reservaEditar.getListadoReservaHab().isEmpty()) {
	    	model.addAttribute("tipoHabitacionSeleccionada",
	    			reservaEditar.getListadoReservaHab().get(0).getHabitacion().getTipo());
	    }
	    
	    if (reservaEditar.getPlanComida() != null) {
	    	model.addAttribute("planComidaSeleccionado", reservaEditar.getPlanComida().getId());
	    }
	    
	    return "formularioreserva";
	}
	   
	@PostMapping("/admin/editar/reserva/{id}")
	public String editarReserva(@PathVariable Long id,
	                             @ModelAttribute Reserva reserva,
	                             @RequestParam(required = false) Integer planComidaId,
	                             Model model) {

	    Optional<Reserva> reservaBuscada = reservaService.findById(id);
	    if (reservaBuscada.isEmpty()) {
	        return "redirect:/admin/reservas";
	    }

	    Reserva reservaEditar = reservaBuscada.get();


	    if (reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
	        model.addAttribute("error", "Las fechas son obligatorias");
	        model.addAttribute("reserva", reservaEditar);
	        model.addAttribute("tiposHabitacion", List.of("Individual", "Doble", "Suite"));
	        model.addAttribute("temporadas", temporadaService.findAll());
	        model.addAttribute("planesComida", planComidaRepository.findAll());
	        model.addAttribute("fechaMinima", LocalDate.now());
	        model.addAttribute("nombreCliente", reservaEditar.getCliente().getNombre());
	        model.addAttribute("editandoReserva", true);
	        return "formularioreserva";
	    }

	    if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio())) {
	        model.addAttribute("error", "La fecha de salida debe ser posterior a la de entrada");
	        model.addAttribute("reserva", reservaEditar);
	        model.addAttribute("tiposHabitacion", List.of("Individual", "Doble", "Suite"));
	        model.addAttribute("temporadas", temporadaService.findAll());
	        model.addAttribute("planesComida", planComidaRepository.findAll());
	        model.addAttribute("fechaMinima", LocalDate.now());
	        model.addAttribute("nombreCliente", reservaEditar.getCliente().getNombre());
	        model.addAttribute("editandoReserva", true);
	        return "formularioreserva";
	    }

	    if (reserva.getNumeroPersonas() < 1) {
	        model.addAttribute("error", "El número de personas debe ser al menos 1");
	        model.addAttribute("reserva", reservaEditar);
	        model.addAttribute("tiposHabitacion", List.of("Individual", "Doble", "Suite"));
	        model.addAttribute("temporadas", temporadaService.findAll());
	        model.addAttribute("planesComida", planComidaRepository.findAll());
	        model.addAttribute("fechaMinima", LocalDate.now());
	        model.addAttribute("nombreCliente", reservaEditar.getCliente().getNombre());
	        model.addAttribute("editandoReserva", true);
	        return "formularioreserva";
	    }

	    reservaEditar.setFechaInicio(reserva.getFechaInicio());
	    reservaEditar.setFechaFin(reserva.getFechaFin());
	    reservaEditar.setNumeroPersonas(reserva.getNumeroPersonas());

	    if (planComidaId != null) {
	        planComidaRepository.findById(planComidaId).ifPresent(reservaEditar::setPlanComida);
	    }

	    reservaEditar.calcularPrecioTotal();
	    reservaService.save(reservaEditar);

	    return "redirect:/admin/reservas";
	}
	
	   @PostMapping("/admin/{id}/eliminar/reserva")
	   public String eliminarReserva(@PathVariable Long id) {
		   reservaService.deleteById(id);
		   return "redirect:/admin/reservas";
	   }
	   
	   
	   
	   @GetMapping("/admin/reservas")
	public String listarReservas(Model model) {
		model.addAttribute("reservas", reservaService.findAll());
		model.addAttribute("estados", EstadoReserva.values());
		return "/admin/reservas";
	}
	   
	   @PostMapping("/admin/{id}/cambiar/estado")
	   public String cambiarEstadoReserva(@PathVariable Long id, @RequestParam EstadoReserva estado) {
		   reservaService.findById(id).ifPresent(reserva -> {
			   reserva.setEstadoReserva(estado);
			   reserva.getListadoReservaHab().forEach(rh -> rh.setEstado(estado));
			   reservaService.save(reserva);
		   });
		   return "redirect:/admin/reservas";
	   }
	   
	   /*el método busca la reserva, busca dentro de ella la habitación concreta, 
	    * guarda el coste extra y las observaciones, recalcula el total y 
	    * vuelve al listado.*/
	   @PostMapping("/admin/reservas/{reservaId}/habitaciones/{reservaHabitacionId}/extras")
	   public String guardarExtrasHabitacion(@PathVariable Long reservaId,
			   								 @PathVariable Long reservaHabitacionId,
			   								 @RequestParam(required = false) Double costeServicios,
			   								 @RequestParam(required = false) String observaciones) {
		   reservaService.findById(reservaId).ifPresent(reserva -> {
			   reserva.getListadoReservaHab().stream()
				   .filter(rh -> rh.getId().equals(reservaHabitacionId))
				   .findFirst()
				   .ifPresent(rh -> {
					   rh.setCosteServicios(costeServicios != null ? costeServicios : 0.0);
					   rh.setObservaciones(observaciones);
					   reserva.calcularPrecioTotal();
					   reservaService.save(reserva);
				   });
		   });
		   
		   return "redirect:/admin/reservas";
	   }

		
	   @PostMapping("/admin/{id}/eliminar/habitaciones")
	   public String borrarHabitacion(@PathVariable int id) {
	       reservaService.eliminarPorHabitacion(id);   // borra las reservas asociadas
	       habitacionService.deleteById(id);            // borra la habitación
	       return "redirect:/admin/habitaciones";
	   }

	   @GetMapping("/admin/editar/habitacion/{id}")
	   public String editarHabitacion(@PathVariable int id, Model model) {
		   Optional<Habitacion> habitacion = habitacionService.findById(id);
		    if (habitacion.isEmpty()) {
		    	return "redirect:/admin/habitaciones";
		    }
		    model.addAttribute("habitacion", habitacion.get());
		    return "/admin/formulariohabitacion";
		}

	   @PostMapping("/admin/editar/habitacion/{id}")
	   public String actualizarHabitacion(@PathVariable("id") int id, @ModelAttribute Habitacion habitacionForm) {
	       
	       Optional<Habitacion> habitacionOpt = habitacionService.findById(id);
	       
	       if (habitacionOpt.isPresent()) {
	           Habitacion habitacionReal = habitacionOpt.get();
	           
	           habitacionReal.setTipo(habitacionForm.getTipo());
	           habitacionReal.setPrecioNoche(habitacionForm.getPrecioNoche());
	           
	           habitacionService.save(habitacionReal);
	       }
	       
	       return "redirect:/admin/habitaciones";
	   }

	   @GetMapping("/admin/{dni}/editar/cliente")
	   public String editarCliente(@PathVariable String dni,Model model) {
		   Optional<Cliente> cliente = clienteService.findById(dni);
		    if (cliente.isEmpty()) {
		    	return "redirect:/admin/clientes";
		    }
		    model.addAttribute("cliente", cliente.get());
		    model.addAttribute("edicionCliente", true);
		    return "cliente";
		}
	   
	   @PostMapping("/admin/{dni}/editar/cliente")
	   public String editarCliente(@PathVariable String dni, @ModelAttribute Cliente cliente) {
	       Optional<Cliente> clienteOpt = clienteService.findById(dni);
	       
	       if (clienteOpt.isPresent()) {
	    	   Cliente clienteReal = clienteOpt.get();
	    	   clienteReal.setNombre(cliente.getNombre());
	    	   clienteReal.setEmail(cliente.getEmail());
	    	   clienteReal.setTelefono(cliente.getTelefono());
	    	   clienteReal.setUsername(cliente.getUsername());
	    	   clienteService.save(clienteReal);
	       }
	       
	       return "redirect:/admin/clientes";
	   }

	   @GetMapping("/admin/nuevo/cliente")
	   public String nuevoCliente(Model model) {
		   model.addAttribute("cliente", new Cliente());
		   model.addAttribute("nuevoClienteAdmin", true);
		   return "cliente";
	   }
	   
	   @PostMapping("/admin/nuevo/cliente")
	   public String guardarClienteAdmin(@Valid @ModelAttribute Cliente cliente,
			   								BindingResult result,
			   							 @RequestParam String password,
			   							 @RequestParam String confirmPassword,
			   							 Model model) {
		   
		   if (result.hasErrors()) {
		        model.addAttribute("nuevoClienteAdmin", true); 
		        return "cliente";
		    }
		   
		   if (!password.equals(confirmPassword)) {
			   model.addAttribute("error", "Las contraseñas no coinciden");
			   model.addAttribute("cliente", cliente);
			   model.addAttribute("nuevoClienteAdmin", true);
			   return "cliente";
		   }
		   
		   if (clienteService.findById(cliente.getDni()).isPresent()) {
			   model.addAttribute("error", "Ya existe un usuario con ese DNI");
			   model.addAttribute("cliente", cliente);
			   model.addAttribute("nuevoClienteAdmin", true);
			   return "cliente";
		   }
		   
		   cliente.setRol(RolUsuario.USER);
		   cliente.setPassword("{noop}" + password);
		   clienteService.save(cliente);
		   
		   return "redirect:/admin/clientes";
	   }

	   @GetMapping("/admin/clientes")
	    public String listarClientes(Model model) {
	        model.addAttribute("clientes", clienteService.findByRolNot(RolUsuario.ADMIN));
	        return "/admin/clientes";
	    }
	   
	   @PostMapping("/admin/{dni}/eliminar/cliente")
	   public String eliminarCliente(@PathVariable String dni) {
		   
		   
		   if(clienteService.findById(dni).get().getUsername().equals("admin")){
			   
			   throw new BorrarAdminException("No se puede borrar el admin base");
		   }
		   
		   
		   
		   
		   
		   clienteService.deleteById(dni);
		   return "redirect:/admin/clientes";
	   }
	   
	   @PostMapping("/admin/agregar/habitacion")
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
	   
	   @GetMapping("/admin/servicios")
	   public String listarServicios(Model model) {
		   model.addAttribute("servicios", servicioService.findAll());
		   return "/admin/servicios";
	   }
	   
	   @PostMapping("/admin/agregar/servicio")
	   public String agregarServicio(@RequestParam String nombre,
			   						 @RequestParam double precio,
			   						 @RequestParam(required = false) String imagen) {
		   Servicio servicio = Servicio.builder()
				   .nombre(nombre)
				   .precio(precio)
				   .imagenUrl(imagen)
				   .build();
		   servicioService.save(servicio);
		   return "redirect:/admin/servicios";
	   }
	   
	   @GetMapping("/admin/editar/servicio/{id}")
	   public String editarServicioForm(@PathVariable Long id, Model model) {
		   Optional<Servicio> servicio = servicioService.findById(id);
		   if (servicio.isEmpty()) {
			   return "redirect:/admin/servicios";
		   }
		   model.addAttribute("servicio", servicio.get());
		   return "/admin/formularioservicio";
	   }

	   @PostMapping("/admin/editar/servicio/{id}")
	   public String actualizarServicio(@PathVariable Long id, @ModelAttribute Servicio servicioForm) {
		   Optional<Servicio> servicioOpt = servicioService.findById(id);

		   if (servicioOpt.isPresent()) {
			   Servicio servicioReal = servicioOpt.get();
			   servicioReal.setNombre(servicioForm.getNombre());
			   servicioReal.setPrecio(servicioForm.getPrecio());
			   servicioReal.setImagenUrl(servicioForm.getImagenUrl());
			   servicioService.save(servicioReal);
		   }

		   return "redirect:/admin/servicios";
	   }

	   @PostMapping("/admin/{id}/eliminar/servicio")
	   public String eliminarServicio(@PathVariable Long id) {
		   servicioService.deleteById(id);
		   return "redirect:/admin/servicios";
	   }

	   @GetMapping("/admin/editar/plancomida/{id}")
	   public String editarPlanComidaForm(@PathVariable Integer id, Model model) {
		   Optional<PlanComida> plan = planComidaService.findById(id);
		   if (plan.isEmpty()) {
			   return "redirect:/admin/planescomida";
		   }
		   model.addAttribute("plan", plan.get());
		   model.addAttribute("tipos", PlanComidaEnum.values());
		   return "/admin/formularioplancomida";
	   }

	   @PostMapping("/admin/editar/plancomida/{id}")
	   public String actualizarPlanComida(@PathVariable Integer id, @ModelAttribute PlanComida planForm) {
		   Optional<PlanComida> planOpt = planComidaService.findById(id);

		   if (planOpt.isPresent()) {
			   PlanComida planReal = planOpt.get();
			   planReal.setTipo(planForm.getTipo());
			   planReal.setPrecio(planForm.getPrecio());
			   planComidaService.save(planReal);
		   }

		   return "redirect:/admin/planescomida";
	   }

	   @PostMapping("/admin/{id}/eliminar/plancomida")
	   public String eliminarPlanComida(@PathVariable Integer id) {
		   planComidaService.deleteById(id);
		   return "redirect:/admin/planescomida";
	   }
	   
	  

}
