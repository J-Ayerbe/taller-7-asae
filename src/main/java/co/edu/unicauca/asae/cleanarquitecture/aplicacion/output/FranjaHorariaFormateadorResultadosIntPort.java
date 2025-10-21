package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import java.util.List;

public interface FranjaHorariaFormateadorResultadosIntPort {
    FranjaHoraria preparar(FranjaHoraria franjaHoraria);
    List<FranjaHoraria> prepararLista(List<FranjaHoraria> franjasHorarias);
    void retornarRespuestaErrorEntidadExiste(String mensaje);
    void retornarRespuestaErrorReglaDeNegocio(String mensaje);
    void retornarRespuestaErrorEntidadNoExiste(String mensaje);
}
