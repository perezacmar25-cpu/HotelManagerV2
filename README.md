<div align="center">

# 🏨 HotelManager

**Sistema de gestión hotelera desarrollado con Java y Spring Boot**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-004088?style=for-the-badge&logo=h2&logoColor=white)

</div>

---

## 📋 Descripción

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

Mario Pérez Acosta
