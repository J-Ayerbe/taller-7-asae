package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.validaciones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTOPeticion.FranjaHorariaDTOPeticion;

public class CapacidadEspacioFisicoValidator implements ConstraintValidator<CapacidadEspacioFisico, FranjaHorariaDTOPeticion> {

    @Autowired
    private GestionarEspacioFisicoGatewayIntPort espacioFisicoGateway;

    @Autowired
    private GestionarCursoGatewayIntPort cursoGateway;

    @Override
    public void initialize(CapacidadEspacioFisico constraintAnnotation) {
    }

    @Override
    public boolean isValid(FranjaHorariaDTOPeticion dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getIdEspacioFisico() == null || dto.getIdCurso() == null) {
            return true; // Use @NotNull for null validation
        }

        try {
            EspacioFisico espacioFisico = espacioFisicoGateway.obtenerPorId(dto.getIdEspacioFisico());
            Curso curso = cursoGateway.obtenerPorId(dto.getIdCurso());

            if (espacioFisico == null || curso == null) {
                return true; // Let other validators handle existence
            }

            // Validar que la capacidad del espacio físico sea mayor o igual a la matrícula estimada
            if (espacioFisico.getCapacidad() < curso.getMatriculaEstimada()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    String.format("La capacidad del espacio físico (%d) es menor que la matrícula estimada del curso (%d)",
                        espacioFisico.getCapacidad(), curso.getMatriculaEstimada()))
                    .addConstraintViolation();
                return false;
            }

            return true;
        } catch (Exception e) {
            return true; // En caso de error, permitir que pase y que otros validadores manejen
        }
    }
}
