package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacioFisicoDTORespuesta {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private Boolean estado;
}
