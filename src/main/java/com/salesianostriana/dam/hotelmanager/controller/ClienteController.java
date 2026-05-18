package com.salesianostriana.dam.hotelmanager.controller;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;

import lombok.RequiredArgsConstructor;
 
@Controller
@RequiredArgsConstructor
public class ClienteController {
 
    private final ClienteService clienteService;
 
    @GetMapping("/registro")
    public String formulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente";
    }
 
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute Cliente cliente,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,  
                          Model model) {
 
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
 
        // hacemos login automático con el cliente recién registrado
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
 
        // mandamos directamente al formulario de reserva
        return "redirect:/reserva/nueva";
    }
 
    @GetMapping("/clientes")
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes";
    }

}