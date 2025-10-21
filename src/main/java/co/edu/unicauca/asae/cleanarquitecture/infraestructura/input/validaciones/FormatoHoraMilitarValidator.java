package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormatoHoraMilitarValidator implements ConstraintValidator<FormatoHoraMilitar, LocalTime> {

    @Override
    public void initialize(FormatoHoraMilitar constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull for null validation
        }

        try {
            // Validar que la hora esté en formato militar (HH:mm donde HH está entre 00-23)
            String horaStr = value.format(DateTimeFormatter.ofPattern("HH:mm"));
            String[] partes = horaStr.split(":");

            if (partes.length != 2) {
                return false;
            }

            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            // Validar que las horas estén entre 00 y 23, y los minutos entre 00 y 59
            return horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59;
        } catch (Exception e) {
            return false;
        }
    }
}
