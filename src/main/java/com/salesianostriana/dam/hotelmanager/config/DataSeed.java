package com.salesianostriana.dam.hotelmanager.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.salesianostriana.dam.hotelmanager.model.Habitacion;
import com.salesianostriana.dam.hotelmanager.model.Servicio;
import com.salesianostriana.dam.hotelmanager.service.HabitacionService;
import com.salesianostriana.dam.hotelmanager.service.ServicioService;
@Configuration
public class DataSeed {
    @Bean
    CommandLineRunner initData(HabitacionService habSer, ServicioService serSer) {
        return args -> {
            if (habSer.findAll().isEmpty()) {
            
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());
                habSer.save(Habitacion.builder().tipo("Individual").precioNoche(55.0).build());

            
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());
                habSer.save(Habitacion.builder().tipo("Doble").precioNoche(100.0).build());

              
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
                habSer.save(Habitacion.builder().tipo("Suite").precioNoche(155.0).build());
            }
            if (serSer.findAll().isEmpty()) {
                serSer.save(Servicio.builder().nombre("Pista de fútbol").precio(15.0).build());
                serSer.save(Servicio.builder().nombre("Pista de pádel").precio(10.0).build());
                serSer.save(Servicio.builder().nombre("Tiro con arco").precio(8.0).build());
                serSer.save(Servicio.builder().nombre("Spa").precio(40).build());
                serSer.save(Servicio.builder().nombre("Gimnasio").precio(0).build());
                serSer.save(Servicio.builder().nombre("Wifi").precio(0).build());
            }
        };
    }
}