package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.CursoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {

    private final ModelMapper modelMapper;

    public CursoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CursoEntity domainToEntity(Curso curso) {
        return modelMapper.map(curso, CursoEntity.class);
    }

    public Curso entityToDomain(CursoEntity cursoEntity) {
        return modelMapper.map(cursoEntity, Curso.class);
    }

    public List<Curso> entitiesToDomain(List<CursoEntity> entities) {
        return entities.stream()
                .map(this::entityToDomain)
                .collect(Collectors.toList());
    }
}
