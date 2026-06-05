<p align="center">
  <img src="https://raw.githubusercontent.com/perezacmar25-cpu/HotelManagerV2/main/src/main/resources/static/img/banner.jpg" alt="Hospedium Hotel Manager V2 Banner" width="100%" style="max-width: 900px; border-radius: 8px;">
</p>

# HotelManagerV2 — Hospedium

> Aplicación web de gestión hotelera construida con Spring Boot 3. Panel de admin completo, reservas inteligentes y experiencia de cliente pulida. Sin bases de datos externas, arranca en segundos.

---

## Stack

Backend   →  Spring Boot 3 · Spring Data JPA · Spring SecurityFrontend  →  Thymeleaf · Bootstrap 5 · JavaScript vanillaBase de datos  →  H2 (en memoria, carga automática al arrancar)Extras    →  Lombok · Bean Validation · Generación de PDF
---

## Arrancar el proyecto

```bash
# 1. Clona o descomprime el proyecto
git clone [https://github.com/perezacmar25-cpu/HotelManagerV2/tree/main](https://github.com/perezacmar25-cpu/HotelManagerV2/tree/main)

# 2. Abre en IntelliJ IDEA (recomendado) o Eclipse

# 3. Ejecuta la clase principal
com.salesianostriana.dam.hotelmanager.HotelManagerV21Application

# 4. Abre el navegador
http://localhost:9000
La base de datos H2 se inicializa automáticamente con datos de prueba gracias a la clase DataSeed. No necesitas configurar nada.Credenciales de pruebaRolUsuarioContraseña🔑 Administradoradminadmin👤 ClienteuseruserConsola H2 → http://localhost:9000/h2-consoleQué puede hacer cada rol👤 Cliente (USER)Registro y loginVer habitaciones disponibles con carrusel de fotosHacer reservas (validación de fechas, aforo y solapamientos incluidos)Añadir / quitar servicios extras en una reservaVer el precio actualizado en tiempo real mientras configura la reservaConsultar sus reservas y descargar el detalle en PDFCancelar una reserva🔑 Administrador (ADMIN)Panel completo con gestión CRUD de:ClientesHabitacionesReservas — incluyendo cambio manual de estadoServicios extrasPlanes de comidaVistas adicionales:Habitaciones más demandadasLlegadas previstas para hoyLógica de preciosEl precio final de una reserva se calcula automáticamente combinando:Precio base de la habitación
  + Ajuste por temporada
  + Plan de comida seleccionado
  + Servicios extras añadidos
─────────────────────────────
= Total de la reserva
El resumen se actualiza en tiempo real en el formulario gracias a un pequeño script en JavaScript. Las validaciones de negocio (solapamiento de fechas, aforo máximo) se aplican tanto en frontend como en backend.Estructura del proyectosrc/
├── main/
│   ├── java/.../
│   │   ├── controller/     # Controladores MVC
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositorios Spring Data
│   │   └── service/        # Lógica de negocio (BaseService / BaseServiceImpl)
│   └── resources/
│       ├── templates/      # Plantillas Thymeleaf
│       └── static/         # CSS, JS e imágenes
SeguridadSpring Security gestiona la autenticación con dos roles diferenciados (ADMIN / USER). Se ha implementado un LoginSuccessHandler personalizado que redirige a cada rol a su vista correspondiente tras el login.NotasArquitectura limpia por capas con patrón Service LayerCódigo comentado en las partes más complejasDiseño responsive (Bootstrap 5)Preloader de carga y mensajes de error amigablesRama activa en Git: main
