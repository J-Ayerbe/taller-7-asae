package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.gateway;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarCursoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.Curso;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.CursoEntity;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers.CursoMapper;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios.CursoRepositoryInt;

import java.util.List;
import java.util.Optional;

@Service
public class GestionarCursoGatewayImplAdapter implements GestionarCursoGatewayIntPort {

    private final CursoRepositoryInt cursoRepository;
    private final CursoMapper cursoMapper;

    public GestionarCursoGatewayImplAdapter(
            CursoRepositoryInt cursoRepository,
            CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    @Override
    public List<Curso> listarPorAsignatura(String nombreAsignatura) {
        List<CursoEntity> cursos = cursoRepository.findByAsignaturaNombre(nombreAsignatura);
        return cursoMapper.entitiesToDomain(cursos);
    }

    @Override
    public boolean existePorId(Integer id) {
        return cursoRepository.existsById(id);
    }

    @Override
    public Curso obtenerPorId(Integer id) {
        Optional<CursoEntity> cursoEntity = cursoRepository.findById(id);
        return cursoEntity.map(cursoMapper::entityToDomain).orElse(null);
    }
}
