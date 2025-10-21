package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTORespuesta;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranjaHorariaDTORespuesta {
    private Integer id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private CursoDTORespuesta curso;
    private EspacioFisicoDTORespuesta espacioFisico;
}
