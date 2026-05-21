<div align="center">

# 🏨 HotelManager

**Sistema de gestión hotelera desarrollado en Java con Spring Boot**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-004088?style=for-the-badge&logo=h2&logoColor=white)

</div>

---

## 📋 Descripción

HotelManager es una aplicación desarrollada en Java con Spring Boot para la gestión integral de un hotel. Permite administrar habitaciones, clientes y reservas, y controlar el estado de cada estancia mediante check-in y check-out.

Desarrollado como proyecto académico aplicando principios de programación orientada a objetos, arquitectura por capas y persistencia con base de datos embebida H2.

---

## ✨ Funcionalidades

- 🚪 **Gestión de habitaciones** — Alta, baja y modificación con tipo, número y estado (libre/ocupada)
- 📅 **Reservas** — Creación y consulta de reservas asociando cliente, habitación y fechas
- 🔑 **Check-in / Check-out** — Registro de entradas y salidas con actualización automática del estado
- 👤 **Gestión de clientes** — Registro y consulta de datos de huéspedes

---

## 🛠️ Stack tecnológico

| Tecnología | Uso |
|---|---|
| Java | Lenguaje principal |
| Spring Boot | Framework de aplicación |
| Maven | Gestión de dependencias y compilación |
| H2 Database | Base de datos embebida (en fichero local) |

---

## 🚀 Cómo ejecutar

**Requisitos:** Java 17+ y Maven instalados.

```bash
# 1. Clona el repositorio
git clone https://github.com/tu-usuario/hotelmanager.git

# 2. Entra en la carpeta
cd hotelmanager

# 3. Compila y arranca
mvn spring-boot:run
```

> La base de datos H2 se crea automáticamente al arrancar. No necesitas configurar nada más.

---

## 👥 Autores

> *(Añade aquí tu nombre o el de tu equipo)*
