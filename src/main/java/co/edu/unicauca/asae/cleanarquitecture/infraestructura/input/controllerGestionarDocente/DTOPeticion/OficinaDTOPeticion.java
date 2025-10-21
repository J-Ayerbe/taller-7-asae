package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTOPeticion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficinaDTOPeticion {

    @NotEmpty(message = "{oficina.nombre.notEmpty}")
    @Size(min = 3, max = 100, message = "{oficina.nombre.size}")
    private String nombre;

    @NotEmpty(message = "{oficina.ubicacion.notEmpty}")
    @Size(min = 3, max = 100, message = "{oficina.ubicacion.size}")
    private String ubicacion;
}
