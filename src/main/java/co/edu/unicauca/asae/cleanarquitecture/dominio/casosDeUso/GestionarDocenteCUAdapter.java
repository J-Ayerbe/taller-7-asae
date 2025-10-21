package co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.input.GestionarDocenteCUIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarDocenteGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.DocenteFormateadorResultadosIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ManejadorValidacion;
import co.edu.unicauca.asae.cleanarquitecture.dominio.casosDeUso.validaciones.ValidadorDocenteCorreoUnico;

public class GestionarDocenteCUAdapter implements GestionarDocenteCUIntPort {

    private final GestionarDocenteGatewayIntPort docenteGateway;
    private final DocenteFormateadorResultadosIntPort formateador;

    public GestionarDocenteCUAdapter(
            GestionarDocenteGatewayIntPort docenteGateway,
            DocenteFormateadorResultadosIntPort formateador) {
        this.docenteGateway = docenteGateway;
        this.formateador = formateador;
    }

    @Override
    public Docente crear(Docente docente) {
        // Construir la cadena de validaciones
        ManejadorValidacion<Docente> validadorCorreo = new ValidadorDocenteCorreoUnico(docenteGateway, formateador);

        // Ejecutar las validaciones
        validadorCorreo.manejar(docente);

        // Si pasa todas las validaciones, guardar
        Docente docenteCreado = docenteGateway.guardar(docente);
        return formateador.preparar(docenteCreado);
    }
}
