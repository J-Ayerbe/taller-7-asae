package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;

public interface DocenteFormateadorResultadosIntPort {
    Docente preparar(Docente docente);
    void retornarRespuestaErrorEntidadExiste(String mensaje);
    void retornarRespuestaErrorReglaDeNegocio(String mensaje);
    void retornarRespuestaErrorEntidadNoExiste(String mensaje);
}
