package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CapacidadEspacioFisicoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CapacidadEspacioFisico {
    String message() default "{capacidadEspacioFisico.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
