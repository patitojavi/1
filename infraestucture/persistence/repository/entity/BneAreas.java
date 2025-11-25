package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "BNE_AREAS", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class BneAreas implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigo;
    private String nombre;
    private Date fecIniVig;
    private Date fecFinVig;
    private Date fecModif;
    private Date fecAlta;
    private List<BneSubareas> bneSubareases = new ArrayList<>(0);

    public BneAreas() {
    }

    public BneAreas(final Integer id) {
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

    @Column(name = "CODIGO", unique = true, nullable = false, length = 2)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 100)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_INI_VIG", length = 10)
    public Date getFecIniVig() {
        return this.fecIniVig;
    }

    public void setFecIniVig(final Date fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_FIN_VIG", length = 10)
    public Date getFecFinVig() {
        return this.fecFinVig;
    }

    public void setFecFinVig(final Date fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIF", nullable = false, length = 23)
    public Date getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Date fecModif) {
        this.fecModif = fecModif;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_ALTA", nullable = false, length = 23)
    public Date getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Date fecAlta) {
        this.fecAlta = fecAlta;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bneAreas")
    @Cascade({ CascadeType.MERGE, CascadeType.REFRESH })
    public List<BneSubareas> getBneSubareases() {
        return this.bneSubareases;
    }

    public void setBneSubareases(final List<BneSubareas> bneSubareases) {
        this.bneSubareases = bneSubareases;
    }
}
