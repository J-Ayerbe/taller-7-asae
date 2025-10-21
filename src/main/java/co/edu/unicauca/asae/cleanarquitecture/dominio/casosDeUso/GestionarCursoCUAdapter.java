package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarCursoCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.CursoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;

import java.util.List;

public class GestionarCursoCUAdapter implements GestionarCursoCUIntPort {

    private final GestionarCursoGatewayIntPort cursoGateway;
    private final CursoFormateadorResultadosIntPort formateador;

    public GestionarCursoCUAdapter(
            GestionarCursoGatewayIntPort cursoGateway,
            CursoFormateadorResultadosIntPort formateador) {
        this.cursoGateway = cursoGateway;
        this.formateador = formateador;
    }

    @Override
    public List<Curso> listarPorAsignatura(String nombreAsignatura) {
        List<Curso> cursos = cursoGateway.listarPorAsignatura(nombreAsignatura);
        return formateador.prepararLista(cursos);
    }
}
