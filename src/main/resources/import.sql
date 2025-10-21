-- Datos de prueba para la aplicación de Gestión Académica
-- Este archivo se ejecuta automáticamente cuando la aplicación se inicia

-- Insertar Asignaturas
INSERT INTO Asignatura (id, nombre) VALUES (1, 'Matematicas');
INSERT INTO Asignatura (id, nombre) VALUES (2, 'Fisica');
INSERT INTO Asignatura (id, nombre) VALUES (3, 'Programacion');

-- Insertar Espacios Físicos
INSERT INTO EspacioFisico (id, nombre, ubicacion, capacidad, estado) VALUES (1, 'Aula 101', 'Edificio A - Primer Piso', 40, true);
INSERT INTO EspacioFisico (id, nombre, ubicacion, capacidad, estado) VALUES (2, 'Laboratorio Fisica', 'Edificio B - Segundo Piso', 25, true);
INSERT INTO EspacioFisico (id, nombre, ubicacion, capacidad, estado) VALUES (3, 'Sala de Computo 1', 'Edificio C - Primer Piso', 30, true);
INSERT INTO EspacioFisico (id, nombre, ubicacion, capacidad, estado) VALUES (4, 'Aula 205', 'Edificio A - Segundo Piso', 35, true);
INSERT INTO EspacioFisico (id, nombre, ubicacion, capacidad, estado) VALUES (5, 'Laboratorio de Redes', 'Edificio C - Segundo Piso', 40, true);

-- Insertar Oficinas
INSERT INTO Oficina (id, nombre, ubicacion) VALUES (1, 'Oficina Decano', 'Edificio Administrativo');
INSERT INTO Oficina (id, nombre, ubicacion) VALUES (2, 'Oficina Coordinacion', 'Edificio A - Segundo Piso');

-- Insertar Personas y Docentes (requiere inserts en multiples tablas por herencia JOIN)
INSERT INTO Persona (id, nombres, apellidos, correo) VALUES (1, 'Pedro', 'Martinez Lopez', 'pedro.martinez@unicauca.edu.co');
INSERT INTO Docente (id, oficina_id) VALUES (1, 1);

INSERT INTO Persona (id, nombres, apellidos, correo) VALUES (2, 'Laura', 'Sanchez Ruiz', 'laura.sanchez@unicauca.edu.co');
INSERT INTO Docente (id, oficina_id) VALUES (2, 2);

INSERT INTO Persona (id, nombres, apellidos, correo) VALUES (3, 'Carlos', 'Rodriguez Perez', 'carlos.rodriguez@unicauca.edu.co');
INSERT INTO Docente (id, oficina_id) VALUES (3, 1);

-- Insertar Cursos
INSERT INTO Curso (id, nombre, matriculaEstimada, asignatura_id) VALUES (1, 'Calculo I - Grupo A', 35, 1);
INSERT INTO Curso (id, nombre, matriculaEstimada, asignatura_id) VALUES (2, 'Fisica Mecanica - Grupo B', 20, 2);
INSERT INTO Curso (id, nombre, matriculaEstimada, asignatura_id) VALUES (3, 'Programacion Java - Grupo C', 28, 3);
INSERT INTO Curso (id, nombre, matriculaEstimada, asignatura_id) VALUES (4, 'Algebra Lineal - Grupo D', 30, 1);

-- Asignar docentes a cursos (tabla intermedia Many-to-Many)
INSERT INTO curso_docente (curso_id, docente_id) VALUES (1, 1);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (2, 2);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (3, 1);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (3, 3);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (4, 2);

-- Insertar Franjas Horarias (con relación Many-to-One a EspacioFisico)
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (1, 'Lunes', '08:00:00', '10:00:00', 1, 1);
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (2, 'Miercoles', '10:00:00', '12:00:00', 1, 1);
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (3, 'Martes', '14:00:00', '16:00:00', 2, 2);
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (4, 'Jueves', '14:00:00', '16:00:00', 2, 2);
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (5, 'Viernes', '08:00:00', '10:00:00', 3, 3);
INSERT INTO FranjaHoraria (id, dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES (6, 'Martes', '10:00:00', '12:00:00', 4, 4);

-- Insertar productos (datos del proyecto anterior)
INSERT INTO `productos` (`id`, `codigo`, `nombre`, `tipo`, `valor`,`createAt`) VALUES (1, '12391', 'Jabon', 'a', 5000, '2025-10-02 13:03:08'), (2, '22332', 'Cartulida', 'p', 5000,'2025-10-02 13:03:08'),(3, '02759', 'Dolex', 'm', 5000,'2025-10-02 13:03:08');