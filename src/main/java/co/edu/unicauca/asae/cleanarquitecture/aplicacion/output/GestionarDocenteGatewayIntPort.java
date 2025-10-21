package co.edu.unicauca.asae.cleanarquitecture.aplicacion.output;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;

public interface GestionarDocenteGatewayIntPort {
    Docente guardar(Docente docente);
    boolean existePorCorreo(String correo);
    boolean existePorId(Integer id);
}
