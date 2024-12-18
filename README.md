# Gestión de solicitudes de ayuda

Este proyecto consiste en una aplicación web diseñada para que los administradores 
municipales puedan gestionar solicitudes de ayuda mediante funcionalidades de creación,
consulta, actualización y eliminación (CRUD). Estas solicitudes abordan diversas 
necesidades, como la limpieza de calles, el suministro de alimentos y agua, o la 
reparación de infraestructuras esenciales. La finalidad principal es asegurar una 
distribución justa y eficiente de los recursos disponibles, promoviendo la recuperación de 
las zonas más afectadas. 

## Caracteristicas

- Administración de solicitudes, municipios, provincias y usuarios.
- Autenticación y autorización de usuarios.
- Jerarquización de prioridades según valor de prioridad.
- Historial de solicitudes completadas.
- CRUD habilitado en solicitudes, municipios y provincias.
- Roles de administrador y usuario para gestionar contenido.
- API REST con Spring Boot y persistencia con JPA.

## Tecnologías

- Front: HTML, CSS, JavaScript y Bootstrap.
- Back: Java, Spring Boot, Spring Data JPA.
- Base de datos: MariaDB.

## Ejecutar proyecto

Clona el proyecto

```bash
  git clone https://github.com/dgarciagonz/microserviciosAyudaMunicipios.git
```

Ve al directorio raiz

```bash
  cd ayuda_municipios
```

Configura las propiedades de la base de datos

```bash
  ayuda_municipios>target>classes>application.properties
```

Inicia el proyecto

```bash
  mvn spring-boot:run 
```
