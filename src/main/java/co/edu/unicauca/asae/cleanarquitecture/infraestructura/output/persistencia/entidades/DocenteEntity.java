package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Docente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DocenteEntity extends PersonaEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST}, optional = true)
    @JoinColumn(name = "oficina_id")
    private OficinaEntity oficina;

    @ManyToMany(mappedBy = "docentes", fetch = FetchType.EAGER)
    private List<CursoEntity> cursos = new ArrayList<>();
}
