package com.salesianostriana.dam.hotelmanager.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FechaFinInicioException  extends RuntimeException{

	public FechaFinInicioException(String mensaje) {
		
		super(mensaje);
		
	}
}
