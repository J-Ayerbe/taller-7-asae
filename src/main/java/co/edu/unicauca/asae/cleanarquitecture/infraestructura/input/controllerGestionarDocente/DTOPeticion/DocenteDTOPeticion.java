package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTOPeticion;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDTOPeticion {

    @NotEmpty(message = "{persona.nombres.notEmpty}")
    @Size(min = 2, max = 100, message = "{persona.nombres.size}")
    private String nombres;

    @NotEmpty(message = "{persona.apellidos.notEmpty}")
    @Size(min = 2, max = 100, message = "{persona.apellidos.size}")
    private String apellidos;

    @NotEmpty(message = "{persona.correo.notEmpty}")
    @Email(message = "{persona.correo.email}")
    @Size(max = 150, message = "{persona.correo.size}")
    private String correo;

    @Valid
    private OficinaDTOPeticion oficina;
}
