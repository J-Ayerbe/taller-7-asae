package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FranjaHorariaMapper {

    private final ModelMapper modelMapper;

    public FranjaHorariaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FranjaHorariaEntity domainToEntity(FranjaHoraria franjaHoraria) {
        return modelMapper.map(franjaHoraria, FranjaHorariaEntity.class);
    }

    public FranjaHoraria entityToDomain(FranjaHorariaEntity franjaHorariaEntity) {
        if (franjaHorariaEntity == null) {
            return null;
        }

        // Mapeo manual para asegurar que se carguen todos los datos
        FranjaHoraria franja = new FranjaHoraria();
        franja.setId(franjaHorariaEntity.getId());
        franja.setDia(franjaHorariaEntity.getDia());
        franja.setHoraInicio(franjaHorariaEntity.getHoraInicio());
        franja.setHoraFin(franjaHorariaEntity.getHoraFin());

        // Mapear curso completo
        if (franjaHorariaEntity.getCurso() != null) {
            franja.setCurso(modelMapper.map(franjaHorariaEntity.getCurso(), co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso.class));
        }

        // Mapear espacio físico completo
        if (franjaHorariaEntity.getEspacioFisico() != null) {
            franja.setEspacioFisico(modelMapper.map(franjaHorariaEntity.getEspacioFisico(), co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico.class));
        }

        return franja;
    }

    public List<FranjaHoraria> entitiesToDomain(List<FranjaHorariaEntity> entities) {
        return entities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }
}
