package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BNE_ESTADOS_VALIDACION_RC")
public class BneEstadosValidacionRC implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nombre;

    public BneEstadosValidacionRC() {

    }

    public BneEstadosValidacionRC(final Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Column(name = "NOMBRE", nullable = false, length = 100)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

}
