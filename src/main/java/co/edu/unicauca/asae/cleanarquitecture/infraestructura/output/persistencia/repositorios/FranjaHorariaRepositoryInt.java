package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

@Repository
public interface FranjaHorariaRepositoryInt extends JpaRepository<FranjaHorariaEntity, Integer> {

    // KEYWORD: Obtener todas las franjas ocupadas por un curso con un id específico
    List<FranjaHorariaEntity> findByCursoId(Integer cursoId);

    // Query para listar franjas horarias por docente (usando JPQL)
    @Query("SELECT f FROM FranjaHorariaEntity f " +
           "JOIN f.curso c " +
           "JOIN c.docentes d " +
           "WHERE d.id = :docenteId")
    List<FranjaHorariaEntity> findByDocenteId(@Param("docenteId") Integer docenteId);

    // QUERY SQL NATIVO: Verificar si un docente está ocupado en un día, hora inicio y hora fin
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
                   "FROM FranjaHoraria f " +
                   "INNER JOIN Curso c ON f.curso_id = c.id " +
                   "INNER JOIN curso_docente cd ON c.id = cd.curso_id " +
                   "WHERE cd.docente_id = :docenteId " +
                   "AND f.dia = :dia " +
                   "AND f.horaInicio < :horaFin " +
                   "AND f.horaFin > :horaInicio",
           nativeQuery = true)
    Integer verificarDisponibilidadDocente(
            @Param("dia") String dia,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin,
            @Param("docenteId") Integer docenteId);

    // QUERY MODIFICACIÓN: Eliminar todas las franjas horarias de un curso en particular
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM FranjaHorariaEntity f WHERE f.curso.id = :cursoId")
    int eliminarFranjasPorCurso(@Param("cursoId") Integer cursoId);
}
