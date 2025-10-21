package co.edu.unicauca.asae.cleanarquitecture.aplicacion.input;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import java.util.List;

public interface GestionarEspacioFisicoCUIntPort {
    List<EspacioFisico> listar(String patron, Integer capacidad);
    boolean actualizarEstado(Integer espacioId, Boolean estado);
}
