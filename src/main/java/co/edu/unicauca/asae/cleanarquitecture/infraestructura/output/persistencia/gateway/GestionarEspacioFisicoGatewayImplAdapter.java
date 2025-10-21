package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.gateway;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.cleanarquitecture.aplicacion.output.GestionarEspacioFisicoGatewayIntPort;
import co.edu.unicauca.asae.cleanarquitecture.dominio.modelos.EspacioFisico;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.EspacioFisicoEntity;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.mappers.EspacioFisicoMapper;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios.EspacioFisicoRepositoryInt;

import java.util.List;
import java.util.Optional;

@Service
public class GestionarEspacioFisicoGatewayImplAdapter implements GestionarEspacioFisicoGatewayIntPort {

    private final EspacioFisicoRepositoryInt espacioFisicoRepository;
    private final EspacioFisicoMapper espacioFisicoMapper;

    public GestionarEspacioFisicoGatewayImplAdapter(
            EspacioFisicoRepositoryInt espacioFisicoRepository,
            EspacioFisicoMapper espacioFisicoMapper) {
        this.espacioFisicoRepository = espacioFisicoRepository;
        this.espacioFisicoMapper = espacioFisicoMapper;
    }

    @Override
    public List<EspacioFisico> listar(String patron, Integer capacidad) {
        List<EspacioFisicoEntity> espacios = espacioFisicoRepository
                .findByNombreStartingWithIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc(patron, capacidad);
        return espacioFisicoMapper.entitiesToDomain(espacios);
    }

    @Override
    @Transactional
    public boolean actualizarEstado(Integer espacioId, Boolean estado) {
        int filasActualizadas = espacioFisicoRepository.actualizarEstadoEspacioFisico(espacioId, estado);
        return filasActualizadas > 0;
    }

    @Override
    public boolean existePorId(Integer id) {
        return espacioFisicoRepository.existsById(id);
    }

    @Override
    public EspacioFisico obtenerPorId(Integer id) {
        Optional<EspacioFisicoEntity> espacioEntity = espacioFisicoRepository.findById(id);
        return espacioEntity.map(espacioFisicoMapper::entityToDomain).orElse(null);
    }
}
