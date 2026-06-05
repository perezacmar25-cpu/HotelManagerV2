<div align="center">

<img src="https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=1200&q=80" width="100%"/>

<br/><br/>

![Spring Boot](https://img.shields.io/badge/Spring%20Boot%203-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap%205-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![H2](https://img.shields.io/badge/H2%20Database-1316BF?style=for-the-badge&logo=h2&logoColor=white)

<br/>

# 🏨 HotelManagerV2 — Hospedium

### *Aplicación web de gestión hotelera construida con Spring Boot 3*
### *Panel de admin completo · Reservas inteligentes · Sin configuración externa*

[![Estado](https://img.shields.io/badge/Estado-Activo-brightgreen?style=flat-square)]()
[![Puerto](https://img.shields.io/badge/Puerto-9000-blue?style=flat-square)]()
[![Licencia](https://img.shields.io/badge/Licencia-MIT-orange?style=flat-square)]()

</div>

---

## Stack
Backend   →  Spring Boot 3 · Spring Data JPA · Spring Security
Frontend  →  Thymeleaf · Bootstrap 5 · JavaScript vanilla
Base de datos  →  H2 (en memoria, carga automática al arrancar)
Extras    →  Lombok · Bean Validation · Generación de PDF

---

## Arrancar el proyecto
```bash
# 1. Clona o descomprime el proyecto
git clone https://github.com/perezacmar25-cpu/HotelManagerV2/tree/main
# 2. Abre en IntelliJ IDEA (recomendado) o Eclipse
# 3. Ejecuta la clase principal
com.salesianostriana.dam.hotelmanager.HotelManagerV21Application
# 4. Abre el navegador
http://localhost:9000
```

> La base de datos H2 se inicializa automáticamente con datos de prueba gracias a la clase `DataSeed`. No necesitas configurar nada.

---

## Credenciales de prueba

| Rol | Usuario | Contraseña |
|-----|---------|------------|
| 🔑 Administrador | `admin` | `admin` |
| 👤 Cliente | `user` | `user` |

Consola H2 → `http://localhost:9000/h2-console`

---

## Qué puede hacer cada rol

### 👤 Cliente (USER)
- Registro y login
- Ver habitaciones disponibles con carrusel de fotos
- Hacer reservas (validación de fechas, aforo y solapamientos incluidos)
- Añadir / quitar servicios extras en una reserva
- Ver el precio actualizado en tiempo real mientras configura la reserva
- Consultar sus reservas y descargar el detalle en PDF
- Cancelar una reserva

### 🔑 Administrador (ADMIN)
Panel completo con gestión CRUD de:
- **Clientes**
- **Habitaciones**
- **Reservas** — incluyendo cambio manual de estado
- **Servicios extras**
- **Planes de comida**

Vistas adicionales:
- Habitaciones más demandadas
- Llegadas previstas para hoy

---

## Lógica de precios

El precio final de una reserva se calcula automáticamente combinando:
Precio base de la habitación

Ajuste por temporada
Plan de comida seleccionado
Servicios extras añadidos
─────────────────────────────
= Total de la reserva


El resumen se actualiza en tiempo real en el formulario gracias a un pequeño script en JavaScript. Las validaciones de negocio (solapamiento de fechas, aforo máximo) se aplican tanto en frontend como en backend.

---

## Estructura del proyecto
src/
├── main/
│   ├── java/.../
│   │   ├── controller/     # Controladores MVC
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositorios Spring Data
│   │   └── service/        # Lógica de negocio (BaseService / BaseServiceImpl)
│   └── resources/
│       ├── templates/      # Plantillas Thymeleaf
│       └── static/         # CSS, JS e imágenes

---

## Seguridad

Spring Security gestiona la autenticación con dos roles diferenciados (`ADMIN` / `USER`). Se ha implementado un `LoginSuccessHandler` personalizado que redirige a cada rol a su vista correspondiente tras el login.

---

## Notas

- Arquitectura limpia por capas con patrón Service Layer
- Código comentado en las partes más complejas
- Diseño responsive (Bootstrap 5)
- Preloader de carga y mensajes de error amigables
- Rama activa en Git: `main`
