package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.formateador;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.EspacioFisicoFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;

import java.util.List;

@Service
public class EspacioFisicoFormateadorResultadosImplAdapter implements EspacioFisicoFormateadorResultadosIntPort {

    @Override
    public List<EspacioFisico> prepararLista(List<EspacioFisico> espaciosFisicos) {
        return espaciosFisicos;
    }
}
