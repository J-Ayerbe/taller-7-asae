package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarFranjaHorariaGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;

public class ValidadorDocenteDisponible extends ManejadorValidacion<FranjaHoraria> {

    private final GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway;
    private final GestionarCursoGatewayIntPort cursoGateway;
    private final FranjaHorariaFormateadorResultadosIntPort formateador;

    public ValidadorDocenteDisponible(
            GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway,
            GestionarCursoGatewayIntPort cursoGateway,
            FranjaHorariaFormateadorResultadosIntPort formateador) {
        this.franjaHorariaGateway = franjaHorariaGateway;
        this.cursoGateway = cursoGateway;
        this.formateador = formateador;
    }

    @Override
    protected boolean validar(FranjaHoraria franjaHoraria) {
        // Obtener el curso completo con los docentes
        Curso curso = cursoGateway.obtenerPorId(franjaHoraria.getCurso().getId());

        if (curso != null && curso.getDocentes() != null) {
            // Verificar disponibilidad de cada docente del curso
            for (Docente docente : curso.getDocentes()) {
                boolean ocupado = franjaHorariaGateway.verificarDisponibilidadDocente(
                    franjaHoraria.getDia(),
                    franjaHoraria.getHoraInicio(),
                    franjaHoraria.getHoraFin(),
                    docente.getId()
                );

                if (ocupado) {
                    this.formateador.retornarRespuestaErrorReglaDeNegocio(
                        "Error: El docente " + docente.getNombres() + " " + docente.getApellidos() +
                        " está ocupado en el día, hora de inicio y hora fin especificados"
                    );
                    return false;
                }
            }
        }
        return true;
    }
}
