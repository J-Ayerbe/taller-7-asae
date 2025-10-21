package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarEspacioFisico.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarEspacioFisicoCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarEspacioFisico.DTORespuesta.EspacioFisicoDTORespuesta;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarEspacioFisico.mappers.EspacioFisicoMapperInfraestructuraDominio;

import java.util.List;

@RestController
@RequestMapping("/api/espacios-fisicos")
public class EspacioFisicoRestController {

    private final GestionarEspacioFisicoCUIntPort gestionarEspacioFisicoCU;
    private final EspacioFisicoMapperInfraestructuraDominio mapper;

    public EspacioFisicoRestController(
            GestionarEspacioFisicoCUIntPort gestionarEspacioFisicoCU,
            EspacioFisicoMapperInfraestructuraDominio mapper) {
        this.gestionarEspacioFisicoCU = gestionarEspacioFisicoCU;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<EspacioFisicoDTORespuesta>> listar(
            @RequestParam(defaultValue = "") String patron,
            @RequestParam(defaultValue = "0") Integer capacidad) {
        List<EspacioFisico> espacios = gestionarEspacioFisicoCU.listar(patron, capacidad);
        List<EspacioFisicoDTORespuesta> respuesta = mapper.domainListToDtoResponseList(espacios);
        return ResponseEntity.ok(respuesta);
    }

    @PatchMapping("/{espacioId}/estado")
    public ResponseEntity<String> actualizarEstado(
            @PathVariable Integer espacioId,
            @RequestParam Boolean estado) {
        boolean actualizado = gestionarEspacioFisicoCU.actualizarEstado(espacioId, estado);
        if (actualizado) {
            return ResponseEntity.ok("Estado actualizado correctamente");
        }
        return ResponseEntity.badRequest().body("No se pudo actualizar el estado");
    }
}
