package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.formateador;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.DocenteFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.EntidadYaExisteException;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.controladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;

@Service
public class DocenteFormateadorResultadosImplAdapter implements DocenteFormateadorResultadosIntPort {

    @Override
    public Docente preparar(Docente docente) {
        return docente;
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
