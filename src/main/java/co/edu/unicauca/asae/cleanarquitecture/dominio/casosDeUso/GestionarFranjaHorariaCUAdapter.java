package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarFranjaHorariaCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarFranjaHorariaGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.FranjaHorariaFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ManejadorValidacion;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ValidadorEntidadesExisten;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ValidadorEspacioFisicoDisponible;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ValidadorDocenteDisponible;

import java.util.List;

public class GestionarFranjaHorariaCUAdapter implements GestionarFranjaHorariaCUIntPort {

    private final GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway;
    private final GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway;
    private final GestionarCursoGatewayIntPort cursoGateway;
    private final FranjaHorariaFormateadorResultadosIntPort formateador;

    public GestionarFranjaHorariaCUAdapter(
            GestionarFranjaHorariaGatewayIntPort franjaHorariaGateway,
            GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway,
            GestionarCursoGatewayIntPort cursoGateway,
            FranjaHorariaFormateadorResultadosIntPort formateador) {
        this.franjaHorariaGateway = franjaHorariaGateway;
        this.espacioFisicoGateway = espacioFisicoGateway;
        this.cursoGateway = cursoGateway;
        this.formateador = formateador;
    }

    @Override
    public FranjaHoraria crear(FranjaHoraria franjaHoraria) {
        // Construir la cadena de validaciones
        ManejadorValidacion<FranjaHoraria> validadorEntidades =
            new ValidadorEntidadesExisten(espacioFisicoGateway, cursoGateway, formateador);
        ManejadorValidacion<FranjaHoraria> validadorEspacio =
            new ValidadorEspacioFisicoDisponible(franjaHorariaGateway, formateador);
        ManejadorValidacion<FranjaHoraria> validadorDocente =
            new ValidadorDocenteDisponible(franjaHorariaGateway, cursoGateway, formateador);

        // Encadenar validadores
        validadorEntidades.setSiguienteManejador(validadorEspacio)
                         .setSiguienteManejador(validadorDocente);

        // Ejecutar las validaciones
        validadorEntidades.manejar(franjaHoraria);

        // Si pasa todas las validaciones, guardar
        FranjaHoraria franjaCreada = franjaHorariaGateway.guardar(franjaHoraria);
        return formateador.preparar(franjaCreada);
    }

    @Override
    public List<FranjaHoraria> listarPorDocente(Integer docenteId) {
        List<FranjaHoraria> franjas = franjaHorariaGateway.listarPorDocente(docenteId);
        return formateador.prepararLista(franjas);
    }

    @Override
    public List<FranjaHoraria> listarPorCurso(Integer cursoId) {
        List<FranjaHoraria> franjas = franjaHorariaGateway.listarPorCurso(cursoId);
        return formateador.prepararLista(franjas);
    }

    @Override
    public List<FranjaHoraria> listarPorCursoConQuery(Integer cursoId) {
        List<FranjaHoraria> franjas = franjaHorariaGateway.obtenerFranjasConEspaciosPorCurso(cursoId);
        return formateador.prepararLista(franjas);
    }

    @Override
    public int eliminarPorCurso(Integer cursoId) {
        return franjaHorariaGateway.eliminarPorCurso(cursoId);
    }
}
