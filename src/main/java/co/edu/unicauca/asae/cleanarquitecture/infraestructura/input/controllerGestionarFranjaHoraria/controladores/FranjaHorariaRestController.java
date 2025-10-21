package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.controladores;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarFranjaHorariaCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTOPeticion.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTORespuesta.FranjaHorariaDTORespuesta;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.mappers.FranjaHorariaMapperInfraestructuraDominio;

import java.util.List;

@RestController
@RequestMapping("/api/franjas-horarias")
@Validated
public class FranjaHorariaRestController {

    private final GestionarFranjaHorariaCUIntPort gestionarFranjaHorariaCU;
    private final FranjaHorariaMapperInfraestructuraDominio mapper;

    public FranjaHorariaRestController(
            GestionarFranjaHorariaCUIntPort gestionarFranjaHorariaCU,
            FranjaHorariaMapperInfraestructuraDominio mapper) {
        this.gestionarFranjaHorariaCU = gestionarFranjaHorariaCU;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<FranjaHorariaDTORespuesta> crear(@Valid @RequestBody FranjaHorariaDTOPeticion franjaDTO) {
        FranjaHoraria franja = mapper.dtoRequestToDomain(franjaDTO);
        FranjaHoraria franjaCreada = gestionarFranjaHorariaCU.crear(franja);
        FranjaHorariaDTORespuesta respuesta = mapper.domainToDtoResponse(franjaCreada);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<FranjaHorariaDTORespuesta>> listarPorDocente(
            @PathVariable @Min(value = 1, message = "{franjaHoraria.docenteId.min}") Integer docenteId) {
        List<FranjaHoraria> franjas = gestionarFranjaHorariaCU.listarPorDocente(docenteId);
        List<FranjaHorariaDTORespuesta> respuesta = mapper.domainListToDtoResponseList(franjas);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<FranjaHorariaDTORespuesta>> listarPorCurso(@PathVariable Integer cursoId) {
        List<FranjaHoraria> franjas = gestionarFranjaHorariaCU.listarPorCurso(cursoId);
        List<FranjaHorariaDTORespuesta> respuesta = mapper.domainListToDtoResponseList(franjas);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/curso/{cursoId}/con-query")
    public ResponseEntity<List<FranjaHorariaDTORespuesta>> listarPorCursoConQuery(@PathVariable Integer cursoId) {
        List<FranjaHoraria> franjas = gestionarFranjaHorariaCU.listarPorCursoConQuery(cursoId);
        List<FranjaHorariaDTORespuesta> respuesta = mapper.domainListToDtoResponseList(franjas);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/curso/{cursoId}")
    public ResponseEntity<String> eliminarPorCurso(@PathVariable Integer cursoId) {
        int cantidad = gestionarFranjaHorariaCU.eliminarPorCurso(cursoId);
        return ResponseEntity.ok(String.format("Se eliminaron %d franjas horarias", cantidad));
    }
}
