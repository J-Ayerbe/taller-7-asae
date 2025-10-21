package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarEspacioFisicoCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.EspacioFisicoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;

import java.util.List;

public class GestionarEspacioFisicoCUAdapter implements GestionarEspacioFisicoCUIntPort {

    private final GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway;
    private final EspacioFisicoFormateadorResultadosIntPort formateador;

    public GestionarEspacioFisicoCUAdapter(
            GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway,
            EspacioFisicoFormateadorResultadosIntPort formateador) {
        this.espacioFisicoGateway = espacioFisicoGateway;
        this.formateador = formateador;
    }

    @Override
    public List<EspacioFisico> listar(String patron, Integer capacidad) {
        List<EspacioFisico> espacios = espacioFisicoGateway.listar(patron, capacidad);
        return formateador.prepararLista(espacios);
    }

    @Override
    public boolean actualizarEstado(Integer espacioId, Boolean estado) {
        return espacioFisicoGateway.actualizarEstado(espacioId, estado);
    }
}
