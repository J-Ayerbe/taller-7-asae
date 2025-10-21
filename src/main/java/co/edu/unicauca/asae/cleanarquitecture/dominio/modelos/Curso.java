package co.edu.unicauca.asae.cleanarquitecture.dominio.modelos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private Integer id;
    private String nombre;
    private Integer matriculaEstimada;
    private Asignatura asignatura;
    private List<Docente> docentes;
    private List<FranjaHoraria> franjasHorarias;
}
