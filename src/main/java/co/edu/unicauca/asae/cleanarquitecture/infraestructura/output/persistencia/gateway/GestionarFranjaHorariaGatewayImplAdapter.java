package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.gateway;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarFranjaHorariaGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.FranjaHoraria;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers.FranjaHorariaMapper;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios.FranjaHorariaRepositoryInt;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios.EspacioFisicoRepositoryInt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;
import java.util.List;

@Service
public class GestionarFranjaHorariaGatewayImplAdapter implements GestionarFranjaHorariaGatewayIntPort {

    private final FranjaHorariaRepositoryInt franjaHorariaRepository;
    private final EspacioFisicoRepositoryInt espacioFisicoRepository;
    private final FranjaHorariaMapper franjaHorariaMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public GestionarFranjaHorariaGatewayImplAdapter(
            FranjaHorariaRepositoryInt franjaHorariaRepository,
            EspacioFisicoRepositoryInt espacioFisicoRepository,
            FranjaHorariaMapper franjaHorariaMapper) {
        this.franjaHorariaRepository = franjaHorariaRepository;
        this.espacioFisicoRepository = espacioFisicoRepository;
        this.franjaHorariaMapper = franjaHorariaMapper;
    }

    @Override
    public FranjaHoraria guardar(FranjaHoraria franjaHoraria) {
        FranjaHorariaEntity franjaEntity = franjaHorariaMapper.domainToEntity(franjaHoraria);
        FranjaHorariaEntity franjaGuardada = franjaHorariaRepository.save(franjaEntity);

        // Flush para asegurar que se guarde en BD
        franjaHorariaRepository.flush();

        // IMPORTANTE: Limpiar el contexto de persistencia para forzar una nueva carga desde BD
        entityManager.clear();

        // Recargar la franja con todos los datos (EAGER fetch trae curso y espacio completos)
        FranjaHorariaEntity franjaCompleta = franjaHorariaRepository.findById(franjaGuardada.getId())
                .orElse(franjaGuardada);

        return franjaHorariaMapper.entityToDomain(franjaCompleta);
    }

    @Override
    public List<FranjaHoraria> listarPorDocente(Integer docenteId) {
        List<FranjaHorariaEntity> franjas = franjaHorariaRepository.findByDocenteId(docenteId);
        return franjaHorariaMapper.entitiesToDomain(franjas);
    }

    @Override
    public List<FranjaHoraria> listarPorCurso(Integer cursoId) {
        List<FranjaHorariaEntity> franjas = franjaHorariaRepository.findByCursoId(cursoId);
        return franjaHorariaMapper.entitiesToDomain(franjas);
    }

    @Override
    public List<FranjaHoraria> obtenerFranjasConEspaciosPorCurso(Integer cursoId) {
        List<FranjaHorariaEntity> franjas = franjaHorariaRepository.findByCursoId(cursoId);
        return franjaHorariaMapper.entitiesToDomain(franjas);
    }

    @Override
    public boolean verificarDisponibilidadEspacioFisico(String dia, LocalTime horaInicio, LocalTime horaFin, Integer espacioId) {
        // Necesitamos consultar al repositorio de EspacioFisico
        // Inyectamos el repositorio en el constructor
        return espacioFisicoRepository.verificarDisponibilidadEspacioFisico(dia, horaInicio, horaFin, espacioId);
    }

    @Override
    public boolean verificarDisponibilidadDocente(String dia, LocalTime horaInicio, LocalTime horaFin, Integer docenteId) {
        Integer resultado = franjaHorariaRepository.verificarDisponibilidadDocente(dia, horaInicio, horaFin, docenteId);
        return resultado != null && resultado > 0;
    }

    @Override
    @Transactional
    public int eliminarPorCurso(Integer cursoId) {
        return franjaHorariaRepository.eliminarFranjasPorCurso(cursoId);
    }
}
