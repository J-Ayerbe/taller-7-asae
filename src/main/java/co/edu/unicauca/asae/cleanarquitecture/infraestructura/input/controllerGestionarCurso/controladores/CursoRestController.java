package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarCursoCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.DTORespuesta.CursoDTORespuesta;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.mappers.CursoMapperInfraestructuraDominio;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final GestionarCursoCUIntPort gestionarCursoCU;
    private final CursoMapperInfraestructuraDominio mapper;

    public CursoRestController(
            GestionarCursoCUIntPort gestionarCursoCU,
            CursoMapperInfraestructuraDominio mapper) {
        this.gestionarCursoCU = gestionarCursoCU;
        this.mapper = mapper;
    }

    @GetMapping("/por-asignatura")
    public ResponseEntity<List<CursoDTORespuesta>> listarPorAsignatura(@RequestParam String nombreAsignatura) {
        List<Curso> cursos = gestionarCursoCU.listarPorAsignatura(nombreAsignatura);
        List<CursoDTORespuesta> respuesta = mapper.domainListToDtoResponseList(cursos);
        return ResponseEntity.ok(respuesta);
    }
}
