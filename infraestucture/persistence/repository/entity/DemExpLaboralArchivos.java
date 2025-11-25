package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEM_EXP_LABORAL_ARCHIVOS")
public class DemExpLaboralArchivos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private DemExpLaboral demExpLaboral;
    private String archivo;
    private String nombre;

    public DemExpLaboralArchivos() {
    }

    public DemExpLaboralArchivos(final long id) {
        this.id = id;
    }

    @Id

    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public DemExpLaboral getDemExpLaboral() {
        return this.demExpLaboral;
    }

    public void setDemExpLaboral(final DemExpLaboral demExpLaboral) {
        this.demExpLaboral = demExpLaboral;
    }

    @Column(name = "ARCHIVO")
    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(final String archivo) {
        this.archivo = archivo;
    }

    @Column(name = "NOMBRE", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

}
