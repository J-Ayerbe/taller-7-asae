package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.EspacioFisicoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspacioFisicoMapper {

    private final ModelMapper modelMapper;

    public EspacioFisicoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EspacioFisicoEntity domainToEntity(EspacioFisico espacioFisico) {
        return modelMapper.map(espacioFisico, EspacioFisicoEntity.class);
    }

    public EspacioFisico entityToDomain(EspacioFisicoEntity espacioFisicoEntity) {
        return modelMapper.map(espacioFisicoEntity, EspacioFisico.class);
    }

    public List<EspacioFisico> entitiesToDomain(List<EspacioFisicoEntity> entities) {
        return entities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }
}
