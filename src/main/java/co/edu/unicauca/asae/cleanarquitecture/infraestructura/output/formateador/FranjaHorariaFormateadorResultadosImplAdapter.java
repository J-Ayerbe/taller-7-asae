package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.formateador;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

import java.util.List;

@Service
public class FranjaHorariaFormateadorResultadosImplAdapter implements FranjaHorariaFormateadorResultadosIntPort {

    @Override
    public FranjaHoraria preparar(FranjaHoraria franjaHoraria) {
        return franjaHoraria;
    }

    @Override
    public List<FranjaHoraria> prepararLista(List<FranjaHoraria> franjasHorarias) {
        return franjasHorarias;
    }

    @Override
    public void retornarRespuestaErrorEntidadExiste(String mensaje) {
        throw new EntidadYaExisteException(mensaje);
    }

    @Override
    public void retornarRespuestaErrorReglaDeNegocio(String mensaje) {
        throw new ReglaNegocioExcepcion(mensaje);
    }

    @Override
    public void retornarRespuestaErrorEntidadNoExiste(String mensaje) {
        throw new EntidadNoExisteException(mensaje);
    }
}
