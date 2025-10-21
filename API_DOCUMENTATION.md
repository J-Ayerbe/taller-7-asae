# API de Gestión Académica - Documentación

## Descripción
API REST desarrollada con Arquitectura Hexagonal que gestiona docentes, franjas horarias, cursos y espacios físicos de una institución educativa.

## Configuración Inicial

### Base de Datos
1. Crear la base de datos MySQL:
```sql
CREATE DATABASE bdGestionAcademica;
```

2. Configurar credenciales en `application.properties` si es necesario

3. La aplicación ejecutará automáticamente el script `import.sql` para cargar datos iniciales

### Ejecución
```bash
mvn spring-boot:run
```

El servidor se iniciará en `http://localhost:5000`

---

## Endpoints de la API

### 1. GESTIÓN DE DOCENTES

#### Crear Docente
- **URL:** `POST /api/docentes`
- **Descripción:** Crea un nuevo docente con su oficina asignada (cascade persist)
- **Body (JSON):**
```json
{
  "nombres": "Juan",
  "apellidos": "Perez Garcia",
  "correo": "juan.perez@unicauca.edu.co",
  "oficina": {
    "nombre": "Oficina 301",
    "ubicacion": "Edificio A - Tercer Piso"
  }
}
```
- **Validaciones:**
  - `nombres`: requerido, entre 2-100 caracteres
  - `apellidos`: requerido, entre 2-100 caracteres
  - `correo`: requerido, formato email válido, único en el sistema
  - `oficina.nombre`: requerido, entre 3-100 caracteres
  - `oficina.ubicacion`: requerido, entre 3-100 caracteres

- **Respuesta exitosa (201 Created):**
```json
{
  "id": 4,
  "nombres": "Juan",
  "apellidos": "Perez Garcia",
  "correo": "juan.perez@unicauca.edu.co",
  "oficina": {
    "id": 3,
    "nombre": "Oficina 301",
    "ubicacion": "Edificio A - Tercer Piso"
  }
}
```

---

### 2. GESTIÓN DE FRANJAS HORARIAS

#### Crear Franja Horaria
- **URL:** `POST /api/franjas-horarias`
- **Descripción:** Crea una nueva franja horaria asociada a un curso y espacio físico
- **Body (JSON):**
```json
{
  "dia": "Lunes",
  "horaInicio": "14:00",
  "horaFin": "16:00",
  "idCurso": 1,
  "idEspacioFisico": 1
}
```
- **Validaciones:**
  - `dia`: requerido
  - `horaInicio`: requerido, formato militar (HH:mm)
  - `horaFin`: requerido, formato militar (HH:mm)
  - `idCurso`: requerido, debe existir
  - `idEspacioFisico`: requerido, debe existir
  - Capacidad del espacio debe ser ≥ matrícula estimada del curso

- **Validaciones de Reglas de Negocio (Cadena de Responsabilidad):**
  1. El espacio físico no debe estar ocupado en el día/hora especificados
  2. Los docentes del curso no deben estar ocupados en el día/hora especificados
  3. El espacio físico, curso y docentes deben existir

- **Respuesta exitosa (201 Created):**
```json
{
  "id": 7,
  "dia": "Lunes",
  "horaInicio": "14:00:00",
  "horaFin": "16:00:00",
  "curso": {
    "id": 1,
    "nombre": "Calculo I - Grupo A",
    "matriculaEstimada": 35,
    "asignatura": {
      "id": 1,
      "nombre": "Matematicas"
    },
    "docentes": [...]
  },
  "espacioFisico": {
    "id": 1,
    "nombre": "Aula 101",
    "ubicacion": "Edificio A - Primer Piso",
    "capacidad": 40,
    "estado": true
  }
}
```

#### Listar Franjas Horarias por Docente (EAGER Loading)
- **URL:** `GET /api/franjas-horarias/docente/{docenteId}`
- **Descripción:** Obtiene todas las franjas horarias asignadas a un docente con sus cursos y espacios físicos
- **Parámetros:**
  - `docenteId` (path): ID del docente (mínimo 1)
- **Ejemplo:** `GET /api/franjas-horarias/docente/1`
- **Respuesta (200 OK):** Array de franjas horarias con datos completos

#### Listar Franjas Horarias por Curso (LAZY Loading)
- **URL:** `GET /api/franjas-horarias/curso/{cursoId}`
- **Descripción:** Obtiene todas las franjas horarias de un curso usando keyword
- **Parámetros:**
  - `cursoId` (path): ID del curso
- **Ejemplo:** `GET /api/franjas-horarias/curso/1`

#### Listar Franjas Horarias por Curso con Query JPQL
- **URL:** `GET /api/franjas-horarias/curso/{cursoId}/con-query`
- **Descripción:** Obtiene franjas horarias, espacios físicos y cursos usando JPQL con JOIN
- **Parámetros:**
  - `cursoId` (path): ID del curso
- **Ejemplo:** `GET /api/franjas-horarias/curso/1/con-query`

#### Eliminar Franjas Horarias por Curso
- **URL:** `DELETE /api/franjas-horarias/curso/{cursoId}`
- **Descripción:** Elimina todas las franjas horarias de un curso usando Query JPQL
- **Parámetros:**
  - `cursoId` (path): ID del curso
- **Ejemplo:** `DELETE /api/franjas-horarias/curso/1`
- **Respuesta (200 OK):**
```json
"Se eliminaron 2 franjas horarias"
```

---

### 3. GESTIÓN DE ESPACIOS FÍSICOS

#### Listar Espacios Físicos (KEYWORD)
- **URL:** `GET /api/espacios-fisicos`
- **Descripción:** Lista espacios físicos filtrados por patrón de nombre y capacidad mínima, ordenados ascendentemente
- **Parámetros (query):**
  - `patron` (opcional, default=""): Patrón de búsqueda para el nombre
  - `capacidad` (opcional, default=0): Capacidad mínima
- **Ejemplos:**
  - `GET /api/espacios-fisicos` - Lista todos
  - `GET /api/espacios-fisicos?patron=Aula&capacidad=30` - Filtra por patrón y capacidad
  - `GET /api/espacios-fisicos?patron=Lab` - Filtra por patrón

- **Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Aula 101",
    "ubicacion": "Edificio A - Primer Piso",
    "capacidad": 40,
    "estado": true
  },
  ...
]
```

#### Actualizar Estado de Espacio Físico (SQL Native Query)
- **URL:** `PATCH /api/espacios-fisicos/{espacioId}/estado`
- **Descripción:** Actualiza el estado (activo/inactivo) de un espacio físico
- **Parámetros:**
  - `espacioId` (path): ID del espacio físico
  - `estado` (query): true/false
- **Ejemplo:** `PATCH /api/espacios-fisicos/1/estado?estado=false`
- **Respuesta (200 OK):**
```json
"Estado actualizado correctamente"
```

---

### 4. GESTIÓN DE CURSOS

#### Listar Cursos por Asignatura (KEYWORD)
- **URL:** `GET /api/cursos/por-asignatura`
- **Descripción:** Obtiene los cursos asociados a una asignatura específica
- **Parámetros (query):**
  - `nombreAsignatura`: Nombre de la asignatura
- **Ejemplo:** `GET /api/cursos/por-asignatura?nombreAsignatura=Matematicas`
- **Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Calculo I - Grupo A",
    "matriculaEstimada": 35,
    "asignatura": {
      "id": 1,
      "nombre": "Matematicas"
    }
  },
  ...
]
```

---

## Validaciones Personalizadas

### 1. Formato Hora Militar
- **Anotación:** `@FormatoHoraMilitar`
- **Descripción:** Valida que la hora esté en formato militar (HH:mm donde HH está entre 00-23)
- **Se aplica a:** `horaInicio` y `horaFin` en FranjaHorariaDTOPeticion

### 2. Capacidad Espacio Físico
- **Anotación:** `@CapacidadEspacioFisico`
- **Descripción:** Valida que la capacidad del espacio físico sea mayor o igual a la matrícula estimada del curso
- **Se aplica a:** FranjaHorariaDTOPeticion (validación a nivel de clase)

---

## Patrón Cadena de Responsabilidades

Las validaciones de reglas de negocio al crear franjas horarias se implementan mediante el patrón Chain of Responsibility:

1. **ValidadorEntidadesExisten**: Verifica que el espacio físico y curso existan
2. **ValidadorEspacioFisicoDisponible**: Verifica que el espacio físico no esté ocupado
3. **ValidadorDocenteDisponible**: Verifica que los docentes del curso no estén ocupados

---

## Manejo de Errores

### Validaciones Automáticas (400 Bad Request)
```json
{
  "nombres": "El nombre no puede estar vacío",
  "correo": "El correo electrónico no tiene un formato válido"
}
```

### Reglas de Negocio (400 Bad Request)
```json
{
  "codigo": "VIOLACION_REGLA_DE_NEGOCIO",
  "mensaje": "Error: El espacio físico está ocupado en el día, hora de inicio y hora fin especificados",
  "statusCode": 400,
  "url": "/api/franjas-horarias",
  "metodo": "POST"
}
```

### Entidad Ya Existe (406 Not Acceptable)
```json
{
  "codigo": "ENTIDAD_YA_EXISTE",
  "mensaje": "Error: Ya existe un docente con el correo electrónico especificado",
  "statusCode": 406,
  "url": "/api/docentes",
  "metodo": "POST"
}
```

### Entidad No Existe (404 Not Found)
```json
{
  "codigo": "ENTIDAD_NO_ENCONTRADA",
  "mensaje": "Error: El espacio físico no existe",
  "statusCode": 404,
  "url": "/api/franjas-horarias",
  "metodo": "POST"
}
```

---

## Internacionalización

La API soporta mensajes de validación en español (es) e inglés (en).

Para cambiar el idioma, configure el header `Accept-Language`:
- `Accept-Language: es` - Mensajes en español
- `Accept-Language: en` - Mensajes en inglés

---

## Características de JPA Implementadas

1. **Keywords:** `findByNombreStartingWithIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc`, `findByAsignaturaNombre`, `findByCursoId`
2. **JPQL Queries:** Verificación de disponibilidad de espacio físico, obtener franjas con espacios
3. **SQL Nativo:** Verificación de disponibilidad de docente, actualización de estado
4. **Cascade Persist:** Al crear docente se crea automáticamente la oficina
5. **Eager/Lazy Loading:** Configurado según requerimientos
6. **Herencia JOINED:** Persona -> Docente
7. **Relaciones Many-to-Many:** Curso-Docente
8. **Relaciones One-to-Many:** Curso-FranjaHoraria

---

## Estructura del Proyecto (Arquitectura Hexagonal)

```
src/main/java/co/edu/unicauca/asae/cleanarquitecture/
├── dominio/
│   ├── modelos/ (Entidades de dominio)
│   └── casosDeUso/ (Lógica de negocio + Validaciones)
├── aplicacion/
│   ├── input/ (Puertos de entrada - interfaces de casos de uso)
│   └── output/ (Puertos de salida - interfaces de gateways y formatters)
└── infraestructura/
    ├── input/ (Adaptadores de entrada - Controllers, DTOs, Mappers, Validadores)
    ├── output/ (Adaptadores de salida - Entities, Repositories, Gateways, Formatters)
    └── configuracion/ (Beans Configuration)
```

---

## Datos de Prueba

La aplicación carga automáticamente datos de prueba desde `import.sql`:
- 3 Asignaturas
- 5 Espacios Físicos
- 2 Oficinas
- 3 Docentes
- 4 Cursos
- 6 Franjas Horarias

Estos datos pueden usarse para probar los endpoints de la API.
