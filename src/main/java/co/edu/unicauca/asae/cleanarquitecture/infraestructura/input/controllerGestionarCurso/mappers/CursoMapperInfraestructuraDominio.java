package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarCurso.DTORespuesta.CursoDTORespuesta;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapperInfraestructuraDominio {

    private final ModelMapper modelMapper;

    public CursoMapperInfraestructuraDominio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CursoDTORespuesta domainToDtoResponse(Curso curso) {
        return modelMapper.map(curso, CursoDTORespuesta.class);
    }

    public List<CursoDTORespuesta> domainListToDtoResponseList(List<Curso> cursos) {
        return cursos.stream()
                .map(this::domainToDtoResponse)
                .collect(Collectors.toList());
    }
}
