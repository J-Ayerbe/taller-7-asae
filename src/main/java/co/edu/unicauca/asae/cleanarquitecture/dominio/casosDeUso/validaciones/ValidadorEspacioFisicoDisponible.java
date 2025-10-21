package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarFranjaHorariaGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;

public class ValidadorEspacioFisicoDisponible extends ManejadorValidacion<FranjaHoraria> {

    private final GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway;
    private final FranjaHorariaFormateadorResultadosIntPort formateador;

    public ValidadorEspacioFisicoDisponible(
            GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway,
            FranjaHorariaFormateadorResultadosIntPort formateador) {
        this.franjaHorariaGateway = franjaHorariaGateway;
        this.formateador = formateador;
    }

    @Override
    protected boolean validar(FranjaHoraria franjaHoraria) {
        boolean ocupado = franjaHorariaGateway.verificarDisponibilidadEspacioFisico(
            franjaHoraria.getDia(),
            franjaHoraria.getHoraInicio(),
            franjaHoraria.getHoraFin(),
            franjaHoraria.getEspacioFisico().getId()
        );

        if (ocupado) {
            this.formateador.retornarRespuestaErrorReglaDeNegocio(
                "Error: El espacio físico está ocupado en el día, hora de inicio y hora fin especificados"
            );
            return false;
        }
        return true;
    }
}
