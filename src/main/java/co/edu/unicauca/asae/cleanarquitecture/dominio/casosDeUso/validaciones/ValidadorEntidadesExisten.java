package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;

public class ValidadorEntidadesExisten extends ManejadorValidacion<FranjaHoraria> {

    private final GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway;
    private final GestionarCursoGatewayIntPort cursoGateway;
    private final FranjaHorariaFormateadorResultadosIntPort formateador;

    public ValidadorEntidadesExisten(
            GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway,
            GestionarCursoGatewayIntPort cursoGateway,
            FranjaHorariaFormateadorResultadosIntPort formateador) {
        this.espacioFisicoGateway = espacioFisicoGateway;
        this.cursoGateway = cursoGateway;
        this.formateador = formateador;
    }

    @Override
    protected boolean validar(FranjaHoraria franjaHoraria) {
        // Validar que el espacio físico exista
        if (!espacioFisicoGateway.existePorId(franjaHoraria.getEspacioFisico().getId())) {
            this.formateador.retornarRespuestaErrorEntidadNoExiste(
                "Error: El espacio físico no existe"
            );
            return false;
        }

        // Validar que el curso exista
        if (!cursoGateway.existePorId(franjaHoraria.getCurso().getId())) {
            this.formateador.retornarRespuestaErrorEntidadNoExiste(
                "Error: El curso no existe"
            );
            return false;
        }

        return true;
    }
}
