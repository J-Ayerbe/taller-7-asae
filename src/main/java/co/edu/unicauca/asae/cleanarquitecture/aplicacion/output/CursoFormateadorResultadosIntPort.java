package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import java.util.List;

public interface CursoFormateadorResultadosIntPort {
    List<Curso> prepararLista(List<Curso> cursos);
}
