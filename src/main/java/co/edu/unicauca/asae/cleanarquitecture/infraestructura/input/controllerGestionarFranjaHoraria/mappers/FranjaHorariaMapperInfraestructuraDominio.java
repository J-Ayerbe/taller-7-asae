package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.mappers;

import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTOPeticion.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarFranjaHoraria.DTORespuesta.FranjaHorariaDTORespuesta;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FranjaHorariaMapperInfraestructuraDominio {

    private final ModelMapper modelMapper;

    public FranjaHorariaMapperInfraestructuraDominio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FranjaHoraria dtoRequestToDomain(FranjaHorariaDTOPeticion franjaDTO) {
        FranjaHoraria franja = new FranjaHoraria();
        franja.setDia(franjaDTO.getDia());
        franja.setHoraInicio(franjaDTO.getHoraInicio());
        franja.setHoraFin(franjaDTO.getHoraFin());

        // Crear curso con solo el ID
        Curso curso = new Curso();
        curso.setId(franjaDTO.getIdCurso());
        franja.setCurso(curso);

        // Crear espacio físico con solo el ID
        EspacioFisico espacioFisico = new EspacioFisico();
        espacioFisico.setId(franjaDTO.getIdEspacioFisico());
        franja.setEspacioFisico(espacioFisico);

        return franja;
    }

    public FranjaHorariaDTORespuesta domainToDtoResponse(FranjaHoraria franja) {
        return modelMapper.map(franja, FranjaHorariaDTORespuesta.class);
    }

    public List<FranjaHorariaDTORespuesta> domainListToDtoResponseList(List<FranjaHoraria> franjas) {
        return franjas.stream()
                .map(this::domainToDtoResponse)
                .collect(Collectors.toList());
    }
}
