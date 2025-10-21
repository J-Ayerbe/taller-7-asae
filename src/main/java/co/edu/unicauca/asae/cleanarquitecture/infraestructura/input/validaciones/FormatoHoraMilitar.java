package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FormatoHoraMilitarValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatoHoraMilitar {
    String message() default "{formatoHoraMilitar.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
