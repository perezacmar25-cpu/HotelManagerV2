package com.salesianostriana.dam.hotelmanager.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
@Configuration
public class DataSeed {
    @Bean
    CommandLineRunner initData(HabitacionService habSer, ServicioService serSer,ClienteService cliSer) {
        return args -> {
            if (habSer.findAll().isEmpty()) {
            
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).build());

            
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).build());

              
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).build());
            }
            if (serSer.findAll().isEmpty()) {
                serSer.save(Servicio.builder().nombre("Pista de voleyball").precio(15.0).imagen("voleyball.jpg").build());
                serSer.save(Servicio.builder().nombre("Pista de pádel").precio(10.0).imagen("padel.jpg").build());
                serSer.save(Servicio.builder().nombre("Tiro con arco").precio(8.0).imagen("arco.jpg").build());
                serSer.save(Servicio.builder().nombre("Spa").precio(40).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Masaje spa").precio(30).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Tratamiento facial").precio(25).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Gimnasio").precio(0).imagen("gimnasio.jpg").build());
                serSer.save(Servicio.builder().nombre("Wifi").precio(0).imagen("recepcion.jpg").build());
            }
            
            if(cliSer.findAll().isEmpty()) {
            	cliSer.save(Cliente.builder()
            			.dni("73854783U")
            			.email("user@gmail.com")
            			.telefono("667555489")
            			.nombre("user")
            			.password("{noop}user")
            			.username("user")
            			.rol(RolUsuario.USER)
            			.build());
            	
            	cliSer.save(Cliente.builder()
                        .dni("00000000A")
                        .nombre("Administrador")
                        .email("admin@hospedium.com")
                        .telefono("000000000")
                        .username("admin")
                        .password("{noop}admin")
                        .rol(RolUsuario.ADMIN)
                        .build());
            }
        };
    }
}
