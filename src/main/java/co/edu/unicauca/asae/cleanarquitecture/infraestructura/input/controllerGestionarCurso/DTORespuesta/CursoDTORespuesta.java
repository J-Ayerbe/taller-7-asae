package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.DTORespuesta;

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
}
