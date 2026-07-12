package com.salesianostriana.dam.hotelmanager.config;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.salesianostriana.dam.hotelmanager.model.Cliente;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.PlanComida;
import com.salesianostriana.dam.hotelmanager.model.PlanComidaEnum;
import com.salesianostriana.dam.hotelmanager.model.Reserva;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.model.Temporada;
import com.salesianostriana.dam.hotelmanager.security.RolUsuario;
import com.salesianostriana.dam.hotelmanager.service.ClienteService;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.PlanComidaService;
import com.salesianostriana.dam.hotelmanager.service.ReservaService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
import com.salesianostriana.dam.hotelmanager.service.TemporadaService;

@Configuration
public class DataSeed {

    @Bean
    CommandLineRunner initData(HabitacionService habSer, ServicioService serSer, ClienteService cliSer, TemporadaService temSer, PlanComidaService planSer, ReservaService reservaSer, PasswordEncoder passwordEncoder) {
        return args -> {
            if (habSer.findAll().isEmpty()) {
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1598928506311-c55ded91a20c?auto=format&fit=crop&w=800&q=80").build());

                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1566665797739-1674de7a421a?auto=format&fit=crop&w=800&q=80").build());

                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).disponible(true).imagenUrl("https://images.unsplash.com/photo-1590490360182-c33d57733427?auto=format&fit=crop&w=800&q=80").build());
            }

            if (serSer.findAll().isEmpty()) {
                serSer.save(Servicio.builder().nombre("Pista de voleyball").precio(10.0).imagenUrl("https://images.unsplash.com/photo-1593787406536-3676a152d9cb?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Pista de pádel").precio(15.0).imagenUrl("https://images.unsplash.com/photo-1626224583764-f87db24ac4ea?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Tiro con arco").precio(8.0).imagenUrl("https://www.shutterstock.com/image-photo/sports-archer-target-bow-arrow-600nw-2267402961.jpg").build());
                serSer.save(Servicio.builder().nombre("Spa").precio(40).imagenUrl("https://images.unsplash.com/photo-1540555700478-4be289fbecef?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Masaje spa").precio(30).imagenUrl("https://images.unsplash.com/photo-1540555700478-4be289fbecef?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Tratamiento facial").precio(25).imagenUrl("https://images.unsplash.com/photo-1540555700478-4be289fbecef?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Gimnasio").precio(0).imagenUrl("https://images.unsplash.com/photo-1534438327276-14e5300c3a48?auto=format&fit=crop&w=800&q=80").build());
                serSer.save(Servicio.builder().nombre("Wifi").precio(0).imagenUrl("https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=800&q=80").build());
            }

            if (cliSer.findAll().isEmpty()) {
                cliSer.save(Cliente.builder()
                        .dni("73854783U")
                        .email("user@gmail.com")
                        .telefono("667555489")
                        .nombre("user")
                        .password(passwordEncoder.encode("user"))
                        .username("user")
                        .rol(RolUsuario.USER)
                        .build());

                cliSer.save(Cliente.builder()
                        .dni("00000000A")
                        .nombre("Administrador")
                        .email("admin@hospedium.com")
                        .telefono("600000000")
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
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
                p1.setPrecio(0);
                planSer.save(p1);

                PlanComida p2 = new PlanComida();
                p2.setTipo(PlanComidaEnum.MEDIA_PENSION);
                p2.setPrecio(25);
                planSer.save(p2);

                PlanComida p3 = new PlanComida();
                p3.setTipo(PlanComidaEnum.PENSION_COMPLETA);
                p3.setPrecio(50);
                planSer.save(p3);

                PlanComida p4 = new PlanComida();
                p4.setTipo(PlanComidaEnum.TODO_INCLUIDO);
                p4.setPrecio(75);
                planSer.save(p4);
            }
            
            if (reservaSer.findByClienteDni("00000000A").isEmpty()) {
                cliSer.findById("00000000A").ifPresent(admin -> {
                    Reserva reservaAdmin = Reserva.builder()
                            .fechaInicio(LocalDate.now().plusDays(15))
                            .fechaFin(LocalDate.now().plusDays(18))
                            .numeroPersonas(2)
                            .cliente(admin)
                            .build();
                    
                    reservaSer.crearReserva(reservaAdmin, "Doble", null);
                });
            }
            
        };
        
    }
}
