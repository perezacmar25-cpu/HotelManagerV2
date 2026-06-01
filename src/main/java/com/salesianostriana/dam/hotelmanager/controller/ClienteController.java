package com.salesianostriana.dam.hotelmanager.controller;
 
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
 
@Controller
@RequiredArgsConstructor
public class ClienteController {
 
    private final ClienteService clienteService;
    private final ReservaService reservaService;
 
    @GetMapping("/registro")
    public String formulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente";
    }
 
    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente,
                          BindingResult result,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,  
                          Model model) {

    	if (result.hasErrors()) {
    		
    	    return "cliente";
    	}
    	
    	if (password == null || password.isBlank() || password.length() < 6) {
    	    model.addAttribute("error", "La contraseña debe tener al menos 6 caracteres");
    	    model.addAttribute("cliente", cliente);
    	    return "cliente";
    	}

        // comprobamos que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            model.addAttribute("cliente", cliente);
            return "cliente";
        }

        // comprobamos que no exista ya un cliente con ese DNI
        if (clienteService.findById(cliente.getDni()).isPresent()) {
            model.addAttribute("error", "Ya existe un usuario con ese DNI");
            model.addAttribute("cliente", cliente);
            return "cliente";
        }

        // asignamos rol USER y contraseña con {noop}
        cliente.setRol(RolUsuario.USER);
        cliente.setPassword("{noop}" + password);
        //noop sirve para comparar la contraseña directamente con la base de datos
        
        // guardamos el cliente en la base de datos
        clienteService.save(cliente);

        //Hecho  con IA:
        // hacemos login automático con el cliente recién registrado
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // mandamos directamente al formulario de reserva
        return "redirect:/reserva/nueva";
    }
    
    @GetMapping("/mis-reservas")
    public String listarReservas(@AuthenticationPrincipal Cliente cliente, Model model) {
        List<Reserva> reservas = reservaService.findByClienteDni(cliente.getDni());
        model.addAttribute("nombreCliente", cliente.getNombre());
        model.addAttribute("reservas", reservas);
        return "reservascliente";
    }
    
    @GetMapping("/{id}/eliminar/reserva")
    public String eliminarReserva(@PathVariable Long id, @AuthenticationPrincipal Cliente cliente) {
        Optional<Reserva> reserva = reservaService.findById(id);
        
        if (reserva.isPresent() && reserva.get().getCliente().getDni().equals(cliente.getDni())) {
            reservaService.deleteById(id);
        }
        
        return "redirect:/misreservas";
    }


}
