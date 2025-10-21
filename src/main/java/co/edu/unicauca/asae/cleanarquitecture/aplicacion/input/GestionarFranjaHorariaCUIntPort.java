package co.edu.unicauca.asae.cleanarquitecture.aplicacion.input;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import java.util.List;

public interface GestionarFranjaHorariaCUIntPort {
    FranjaHoraria crear(FranjaHoraria franjaHoraria);
    List<FranjaHoraria> listarPorDocente(Integer docenteId);
    List<FranjaHoraria> listarPorCurso(Integer cursoId);
    List<FranjaHoraria> listarPorCursoConQuery(Integer cursoId);
    int eliminarPorCurso(Integer cursoId);
}
