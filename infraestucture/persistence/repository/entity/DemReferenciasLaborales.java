package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEM_REFERENCIAS_LABORALES")
public class DemReferenciasLaborales implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private PerPersonas perPersonas;
    private String nombre;
    private String puesto;
    private String empresa;
    private String telefono;
    private String email;

    public DemReferenciasLaborales() {
    }

    public DemReferenciasLaborales(final Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    public PerPersonas getPerPersonas() {
        return this.perPersonas;
    }

    public void setPerPersonas(final PerPersonas perPersonas) {
        this.perPersonas = perPersonas;
    }

    @Column(name = "NOMBRE", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "PUESTO", nullable = false)
    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(final String puesto) {
        this.puesto = puesto;
    }

    @Column(name = "EMPRESA", nullable = false)
    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(final String empresa) {
        this.empresa = empresa;
    }

    @Column(name = "TELEFONO", nullable = false)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "EMAIL", nullable = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
