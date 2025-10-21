package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import java.util.List;

public interface GestionarCursoGatewayIntPort {
    List<Curso> listarPorAsignatura(String nombreAsignatura);
    boolean existePorId(Integer id);
    Curso obtenerPorId(Integer id);
}
