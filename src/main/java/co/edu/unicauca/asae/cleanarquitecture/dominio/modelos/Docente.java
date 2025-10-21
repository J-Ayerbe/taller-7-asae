package co.edu.unicauca.asae.cleanarquitecture.dominio.modelos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Docente extends Persona {
    private Oficina oficina;
    private List<Curso> cursos;
}
