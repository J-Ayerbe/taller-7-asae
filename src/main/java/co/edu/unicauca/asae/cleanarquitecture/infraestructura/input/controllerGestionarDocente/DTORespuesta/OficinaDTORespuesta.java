package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficinaDTORespuesta {
    private Integer id;
    private String nombre;
    private String ubicacion;
}
