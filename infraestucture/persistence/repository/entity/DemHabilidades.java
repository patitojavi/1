package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "DEM_HABILIDADES")
public class DemHabilidades implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private PerPersonas perPersonas;
    private Date fecAlta;
    private Date fecModif;
    private String habilidad;

    public DemHabilidades() {
    }

    public DemHabilidades(final Long id) {
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_ALTA", nullable = false, length = 23)
    public Date getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIF", nullable = false, length = 23)
    public Date getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Date fecModif) {
        this.fecModif = fecModif;
    }

    @Column(name = "HABILIDAD", length = 200)
    public String getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(final String habilidad) {
        this.habilidad = habilidad;
    }

}
