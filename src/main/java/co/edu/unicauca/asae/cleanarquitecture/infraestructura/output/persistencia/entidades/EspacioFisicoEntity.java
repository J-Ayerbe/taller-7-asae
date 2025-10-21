package co.edu.unicauca.asae.cleanarquitecture.infraestructura.output.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EspacioFisico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EspacioFisicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String ubicacion;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private Boolean estado = true;
}
