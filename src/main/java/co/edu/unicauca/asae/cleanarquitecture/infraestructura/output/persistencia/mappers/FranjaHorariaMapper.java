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
        return modelMapper.map(franjaHorariaEntity, FranjaHoraria.class);
    }

    public List<FranjaHoraria> entitiesToDomain(List<FranjaHorariaEntity> entities) {
        return entities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }
}
