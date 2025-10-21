package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FranjaHoraria")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FranjaHorariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String dia;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "espacio_fisico_id")
    private EspacioFisicoEntity espacioFisico;
}
