package co.edu.unicauca.asae.cleanarquitecture.dominio.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacioFisico {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private Boolean estado;
}
