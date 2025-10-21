package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import java.time.LocalTime;
import java.util.List;

public interface GestionarFranjaHorariaGatewayIntPort {
    FranjaHoraria guardar(FranjaHoraria franjaHoraria);
    List<FranjaHoraria> listarPorDocente(Integer docenteId);
    List<FranjaHoraria> listarPorCurso(Integer cursoId);
    List<FranjaHoraria> obtenerFranjasConEspaciosPorCurso(Integer cursoId);
    boolean verificarDisponibilidadEspacioFisico(String dia, LocalTime horaInicio, LocalTime horaFin, Integer espacioId);
    boolean verificarDisponibilidadDocente(String dia, LocalTime horaInicio, LocalTime horaFin, Integer docenteId);
    int eliminarPorCurso(Integer cursoId);
}
