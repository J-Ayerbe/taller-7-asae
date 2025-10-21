package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.controladores;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarDocenteCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTOPeticion.DocenteDTOPeticion;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTORespuesta.DocenteDTORespuesta;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.mappers.DocenteMapperInfraestructuraDominio;

@RestController
@RequestMapping("/api/docentes")
public class DocenteRestController {

    private final GestionarDocenteCUIntPort gestionarDocenteCU;
    private final DocenteMapperInfraestructuraDominio mapper;

    public DocenteRestController(
            GestionarDocenteCUIntPort gestionarDocenteCU,
            DocenteMapperInfraestructuraDominio mapper) {
        this.gestionarDocenteCU = gestionarDocenteCU;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DocenteDTORespuesta> crear(@Valid @RequestBody DocenteDTOPeticion docenteDTO) {
        Docente docente = mapper.dtoRequestToDomain(docenteDTO);
        Docente docenteCreado = gestionarDocenteCU.crear(docente);
        DocenteDTORespuesta respuesta = mapper.domainToDtoResponse(docenteCreado);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
