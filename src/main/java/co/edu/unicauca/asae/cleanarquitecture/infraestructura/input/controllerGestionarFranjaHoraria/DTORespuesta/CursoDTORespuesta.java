package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTORespuesta;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTORespuesta {
    private Integer id;
    private String nombre;
    private Integer matriculaEstimada;
    private AsignaturaDTORespuesta asignatura;
    private List<DocenteDTORespuesta> docentes;
}
