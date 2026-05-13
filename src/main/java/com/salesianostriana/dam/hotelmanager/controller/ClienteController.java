package com.salesianostriana.dam.hotelmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
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
	    public String guardar(@ModelAttribute Cliente cliente ,Model model) {
	        clienteService.save(cliente);
	        return "redirect:/";
	
}
	    
	    
	    @GetMapping("/clientes")
	    public String listar(Model model) {
	        model.addAttribute("clientes", clienteService.findAll());
	        return "clientes";
	 
	    }
	    
	       
		     @GetMapping("/")
		     public String indice() {
		    	 return "index";
		     }
	    
	    
	    
}