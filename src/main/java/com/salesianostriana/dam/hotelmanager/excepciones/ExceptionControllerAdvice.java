package com.salesianostriana.dam.hotelmanager.excepciones;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(FechaFinInicioException.class)
	public String finAntesQueInicio(FechaFinInicioException ex,Model model) {
		model.addAttribute("errorTitulo", "Fechas incorrectas");
		model.addAttribute("errorMensaje", "La fecha fin no puede ser antes que la de inicio");
		return "error";

		
	}

}
