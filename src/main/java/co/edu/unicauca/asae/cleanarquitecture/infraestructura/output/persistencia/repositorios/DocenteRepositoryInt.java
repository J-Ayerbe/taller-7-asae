package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades.DocenteEntity;

@Repository
public interface DocenteRepositoryInt extends JpaRepository<DocenteEntity, Integer> {
    boolean existsByCorreo(String correo);
}
