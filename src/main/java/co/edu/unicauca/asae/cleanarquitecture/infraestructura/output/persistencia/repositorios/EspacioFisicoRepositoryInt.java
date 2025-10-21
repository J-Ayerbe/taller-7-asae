package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.EspacioFisicoEntity;

@Repository
public interface EspacioFisicoRepositoryInt extends JpaRepository<EspacioFisicoEntity, Integer> {

    // KEYWORD: Listar espacios físicos ordenados por nombre ascendente,
    // que comiencen con un patrón, ignorando mayúsculas/minúsculas
    // y que la capacidad sea mayor o igual que el parámetro
    List<EspacioFisicoEntity> findByNombreStartingWithIgnoreCaseAndCapacidadGreaterThanEqualOrderByNombreAsc(
            String patron, Integer capacidad);

    // QUERY JPQL: Verificar si un espacio físico está ocupado en un día, hora inicio y hora fin
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
           "FROM FranjaHorariaEntity f " +
           "WHERE f.espacioFisico.id = :espacioId " +
           "AND f.dia = :dia " +
           "AND f.horaInicio < :horaFin " +
           "AND f.horaFin > :horaInicio")
    Boolean verificarDisponibilidadEspacioFisico(
            @Param("dia") String dia,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin,
            @Param("espacioId") Integer espacioId);

    // QUERY MODIFICACIÓN: Actualizar estado de un espacio físico (activo/inactivo)
    @Modifying(clearAutomatically = true)
    @Query("UPDATE EspacioFisicoEntity e SET e.estado = :estado WHERE e.id = :espacioId")
    int actualizarEstadoEspacioFisico(
            @Param("espacioId") Integer espacioId,
            @Param("estado") Boolean estado);
}
