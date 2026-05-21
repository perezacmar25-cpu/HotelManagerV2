<div align="center">

# 🏨 HotelManager

**Sistema de gestión hotelera desarrollado en Java**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-004088?style=for-the-badge&logo=h2&logoColor=white)

</div>

---

## 📋 Descripción

HotelManager es una aplicación de escritorio desarrollada en Java para la gestión integral de un hotel. Permite administrar habitaciones, clientes y reservas, así como registrar el check-in y check-out de los huéspedes de forma sencilla.

El proyecto ha sido desarrollado como trabajo académico, aplicando principios de programación orientada a objetos y persistencia de datos con base de datos embebida H2.

---

## ⚙️ Funcionalidades

- **Gestión de habitaciones** — Alta, baja y modificación de habitaciones con su tipo, número y estado (libre/ocupada)
- **Gestión de clientes** — Registro de clientes con sus datos personales
- **Reservas** — Creación y consulta de reservas asociando cliente, habitación y fechas
- **Check-in / Check-out** — Registro de entrada y salida de huéspedes con actualización automática del estado de la habitación

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java | Lenguaje principal |
| Maven | Gestión de dependencias y compilación |
| H2 | Base de datos embebida (en fichero local) |

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos previos

- Java 17 o superior
- Maven instalado (`mvn -v` para verificar)

### Pasos

```bash
# 1. Clona el repositorio
git clone https://github.com/tu-usuario/hotelmanager.git
cd hotelmanager

# 2. Compila el proyecto
mvn clean install

# 3. Ejecuta la aplicación
mvn exec:java
```

> La base de datos H2 se crea automáticamente en local al arrancar la aplicación por primera vez. No es necesaria ninguna configuración adicional.

---

## 🗂️ Estructura del proyecto

```
hotelmanager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hotelmanager/
│   │   │       ├── model/          # Clases de dominio (Habitacion, Cliente, Reserva...)
│   │   │       ├── repository/     # Acceso a base de datos
│   │   │       ├── service/        # Lógica de negocio
│   │   │       └── Main.java       # Punto de entrada
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## 👥 Autores

> *(Añade aquí tu nombre o el de tu equipo)*

---

## 📄 Licencia

Proyecto académico — sin licencia de uso comercial.
