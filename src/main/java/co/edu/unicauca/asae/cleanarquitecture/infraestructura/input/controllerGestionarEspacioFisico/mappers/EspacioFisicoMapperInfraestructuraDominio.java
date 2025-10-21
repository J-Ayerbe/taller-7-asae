package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarEspacioFisico.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarEspacioFisico.DTORespuesta.EspacioFisicoDTORespuesta;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EspacioFisicoMapperInfraestructuraDominio {

    private final ModelMapper modelMapper;

    public EspacioFisicoMapperInfraestructuraDominio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EspacioFisicoDTORespuesta domainToDtoResponse(EspacioFisico espacioFisico) {
        return modelMapper.map(espacioFisico, EspacioFisicoDTORespuesta.class);
    }

    public List<EspacioFisicoDTORespuesta> domainListToDtoResponseList(List<EspacioFisico> espacios) {
        return espacios.stream()
                .map(this::domainToDtoResponse)
                .collect(Collectors.toList());
    }
}
