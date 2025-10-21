package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTOPeticion;

import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones.CapacidadEspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones.FormatoHoraMilitar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CapacidadEspacioFisico
public class FranjaHorariaDTOPeticion {

    @NotEmpty(message = "{franjaHoraria.dia.notEmpty}")
    private String dia;

    @NotNull(message = "{franjaHoraria.horaInicio.notNull}")
    @FormatoHoraMilitar
    private LocalTime horaInicio;

    @NotNull(message = "{franjaHoraria.horaFin.notNull}")
    @FormatoHoraMilitar
    private LocalTime horaFin;

    @NotNull(message = "{franjaHoraria.idCurso.notNull}")
    private Integer idCurso;

    @NotNull(message = "{franjaHoraria.idEspacioFisico.notNull}")
    private Integer idEspacioFisico;
}
