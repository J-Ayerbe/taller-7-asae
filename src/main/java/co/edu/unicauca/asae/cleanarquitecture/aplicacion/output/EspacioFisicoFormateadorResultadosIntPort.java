package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import java.util.List;

public interface EspacioFisicoFormateadorResultadosIntPort {
    List<EspacioFisico> prepararLista(List<EspacioFisico> espaciosFisicos);
}
