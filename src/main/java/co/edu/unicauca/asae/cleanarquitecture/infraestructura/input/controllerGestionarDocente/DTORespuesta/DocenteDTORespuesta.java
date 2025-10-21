package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDTORespuesta {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String correo;
    private OficinaDTORespuesta oficina;
}
