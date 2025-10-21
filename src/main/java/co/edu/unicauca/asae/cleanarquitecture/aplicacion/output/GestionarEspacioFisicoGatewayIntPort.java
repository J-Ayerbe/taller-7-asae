package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import java.util.List;

public interface GestionarEspacioFisicoGatewayIntPort {
    List<EspacioFisico> listar(String patron, Integer capacidad);
    boolean actualizarEstado(Integer espacioId, Boolean estado);
    boolean existePorId(Integer id);
    EspacioFisico obtenerPorId(Integer id);
}
