package com.salesianostriana.dam.hotelmanager.config;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.PlanComida;
import com.salesianostriana.dam.hotelmanager.model.PlanComidaEnum;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.model.Temporada;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.PlanComidaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
import com.salesianostriana.dam.hotelmanager.service.TemporadaService;

@Configuration
public class DataSeed {

    @Bean
    CommandLineRunner initData(HabitacionService habSer, ServicioService serSer, ClienteService cliSer, TemporadaService temSer,PlanComidaService planSer) {
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
                serSer.save(Servicio.builder().nombre("Pista de voleyball").precio(10.0).imagen("voleyball.jpg").build());
                serSer.save(Servicio.builder().nombre("Pista de pádel").precio(15.0).imagen("padel.jpg").build());
                serSer.save(Servicio.builder().nombre("Tiro con arco").precio(8.0).imagen("arco.jpg").build());
                serSer.save(Servicio.builder().nombre("Spa").precio(40).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Masaje spa").precio(30).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Tratamiento facial").precio(25).imagen("spa.jpg").build());
                serSer.save(Servicio.builder().nombre("Gimnasio").precio(0).imagen("gimnasio.jpg").build());
                serSer.save(Servicio.builder().nombre("Wifi").precio(0).imagen("recepcion.jpg").build());
            }

            if (cliSer.findAll().isEmpty()) {
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
                        .telefono("600000000")
                        .username("admin")
                        .password("{noop}admin")
                        .rol(RolUsuario.ADMIN)
                        .build());
            }

            if (temSer.findAll().isEmpty()) {
                temSer.save(Temporada.builder()
                        .nombre("Temporada alta")
                        .fechaInicio(LocalDate.of(2026, 6, 1))
                        .fechaFin(LocalDate.of(2026, 9, 30))
                        .multiplicador(1.5)
                        .build());
                temSer.save(Temporada.builder()
                        .nombre("Temporada baja")
                        .fechaInicio(LocalDate.of(2026, 10, 1))
                        .fechaFin(LocalDate.of(2027, 5, 31))
                        .multiplicador(1.0)
                        .build());
            }
            if (planSer.findAll().isEmpty()) {
                PlanComida p1 = new PlanComida();
                p1.setTipo(PlanComidaEnum.SOLO_DESAYUNO);
                planSer.save(p1);

                PlanComida p2 = new PlanComida();
                p2.setTipo(PlanComidaEnum.MEDIA_PENSION);
                planSer.save(p2);

                PlanComida p3 = new PlanComida();
                p3.setTipo(PlanComidaEnum.PENSION_COMPLETA);
                planSer.save(p3);

                PlanComida p4 = new PlanComida();
                p4.setTipo(PlanComidaEnum.TODO_INCLUIDO);
                planSer.save(p4);
            }
            
        };
        
    }
}