package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.formateador;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.CursoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;

import java.util.List;

@Service
public class CursoFormateadorResultadosImplAdapter implements CursoFormateadorResultadosIntPort {

    @Override
    public List<Curso> prepararLista(List<Curso> cursos) {
        return cursos;
    }
}
