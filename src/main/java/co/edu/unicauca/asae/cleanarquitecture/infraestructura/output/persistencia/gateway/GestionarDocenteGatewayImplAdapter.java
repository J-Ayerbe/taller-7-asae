package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.gateway;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarDocenteGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Docente;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.DocenteEntity;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers.DocenteMapper;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios.DocenteRepositoryInt;

@Service
public class GestionarDocenteGatewayImplAdapter implements GestionarDocenteGatewayIntPort {

    private final DocenteRepositoryInt docenteRepository;
    private final DocenteMapper docenteMapper;

    public GestionarDocenteGatewayImplAdapter(
            DocenteRepositoryInt docenteRepository,
            DocenteMapper docenteMapper) {
        this.docenteRepository = docenteRepository;
        this.docenteMapper = docenteMapper;
    }

    @Override
    public Docente guardar(Docente docente) {
        DocenteEntity docenteEntity = docenteMapper.domainToEntity(docente);
        DocenteEntity docenteGuardado = docenteRepository.save(docenteEntity);
        return docenteMapper.entityToDomain(docenteGuardado);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return docenteRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existePorId(Integer id) {
        return docenteRepository.existsById(id);
    }
}
