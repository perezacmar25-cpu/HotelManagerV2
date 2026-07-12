

# HotelManagerV2 — Hospedium

> Aplicación web de gestión hotelera construida con Spring Boot 3. Panel de admin completo, reservas inteligentes y experiencia de cliente pulida. Sin bases de datos externas, arranca en segundos.

---

## Stack

HotelManager es una aplicación para gestionar un hotel: habitaciones, clientes, reservas, servicios y planes de comida. Incluye un área de administración para consultar y actualizar esta información.

Proyecto académico desarrollado aplicando programación orientada a objetos, arquitectura por capas y persistencia con H2.

---

## ✨ Funcionalidades

- 🛏️ **Habitaciones** — Alta, edición, eliminación y consulta de disponibilidad.
- 📅 **Reservas** — Creación, edición y gestión de estados.
- 👤 **Clientes** — Registro, edición y consulta de huéspedes.
- 🧾 **Servicios y planes de comida** — Gestión de extras asociados a la estancia.
- 📊 **Administración** — Consulta de llegadas y habitaciones más utilizadas.

---

## 🛠️ Stack tecnológico

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot | Framework de aplicación |
| Thymeleaf | Plantillas web del servidor |
| Spring Security | Autenticación y autorización |
| Maven | Gestión de dependencias y compilación |
| H2 Database | Base de datos embebida en memoria |

---

## 🚀 Cómo ejecutar

**Requisito:** Java 21 o superior.

```bash
# En la carpeta del proyecto
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación estará disponible en `http://localhost:9000`. La base de datos se crea automáticamente al iniciar la aplicación.

---

## 👥 Autor

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

```
Precio base de la habitación
  + Ajuste por temporada
  + Plan de comida seleccionado
  + Servicios extras añadidos
─────────────────────────────
= Total de la reserva
```

El resumen se actualiza en tiempo real en el formulario gracias a un pequeño script en JavaScript. Las validaciones de negocio (solapamiento de fechas, aforo máximo) se aplican tanto en frontend como en backend.

---

## Estructura del proyecto

```
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
```

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
