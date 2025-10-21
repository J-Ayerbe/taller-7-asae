package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Curso")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Integer matriculaEstimada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asignatura_id")
    private AsignaturaEntity asignatura;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "curso_docente",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    private List<DocenteEntity> docentes = new ArrayList<>();

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FranjaHorariaEntity> franjasHorarias = new ArrayList<>();
}
