package co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTOPeticion.DocenteDTOPeticion;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.input.controllerGestionarDocente.DTORespuesta.DocenteDTORespuesta;

@Component
public class DocenteMapperInfraestructuraDominio {

    private final ModelMapper modelMapper;

    public DocenteMapperInfraestructuraDominio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Docente dtoRequestToDomain(DocenteDTOPeticion docenteDTO) {
        return modelMapper.map(docenteDTO, Docente.class);
    }

    public DocenteDTORespuesta domainToDtoResponse(Docente docente) {
        return modelMapper.map(docente, DocenteDTORespuesta.class);
    }
}
