package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.DocenteEntity;

@Component
public class DocenteMapper {

    private final ModelMapper modelMapper;

    public DocenteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DocenteEntity domainToEntity(Docente docente) {
        return modelMapper.map(docente, DocenteEntity.class);
    }

    public Docente entityToDomain(DocenteEntity docenteEntity) {
        return modelMapper.map(docenteEntity, Docente.class);
    }
}
