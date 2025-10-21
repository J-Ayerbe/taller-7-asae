package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarDocenteGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.DocenteFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;

public class ValidadorDocenteCorreoUnico extends ManejadorValidacion<Docente> {

    private final GestionarDocenteGatewayIntPort docenteGateway;
    private final DocenteFormateadorResultadosIntPort formateador;

    public ValidadorDocenteCorreoUnico(
            GestionarDocenteGatewayIntPort docenteGateway,
            DocenteFormateadorResultadosIntPort formateador) {
        this.docenteGateway = docenteGateway;
        this.formateador = formateador;
    }

    @Override
    protected boolean validar(Docente docente) {
        if (docenteGateway.existePorCorreo(docente.getCorreo())) {
            this.formateador.retornarRespuestaErrorEntidadExiste(
                "Error: Ya existe un docente con el correo electrónico especificado"
            );
            return false;
        }
        return true;
    }
}
