package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.CursoEntity;
import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.FranjaHorariaEntity;

@Repository
public interface CursoRepositoryInt extends JpaRepository<CursoEntity, Integer> {

    // KEYWORD: Obtener cursos por nombre de asignatura
    List<CursoEntity> findByAsignaturaNombre(String nombreAsignatura);

    // QUERY JPQL: Obtener datos completos de curso con franjas y espacios físicos por id del curso
    @Query("SELECT DISTINCT c FROM CursoEntity c " +
           "LEFT JOIN FETCH c.franjasHorarias " +
           "WHERE c.id = :cursoId")
    CursoEntity obtenerCursoConFranjasYEspacios(@Param("cursoId") Integer cursoId);

    @Query("SELECT DISTINCT f FROM FranjaHorariaEntity f " +
           "LEFT JOIN FETCH f.espacioFisico " +
           "WHERE f.curso.id = :cursoId")
    List<FranjaHorariaEntity> obtenerFranjasConEspaciosPorCurso(@Param("cursoId") Integer cursoId);
}
