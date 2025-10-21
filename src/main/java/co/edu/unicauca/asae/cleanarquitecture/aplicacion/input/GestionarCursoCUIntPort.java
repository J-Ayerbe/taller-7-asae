package co.edu.unicauca.asae.cleanarquitecture.aplicacion.input;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import java.util.List;

public interface GestionarCursoCUIntPort {
    List<Curso> listarPorAsignatura(String nombreAsignatura);
}
