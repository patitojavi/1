package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jakarta.persistence.Transient;

/**
 * Condicion laboral actual del postulante
 */
@Entity
@Table(name = "DEM_COND_LAB")
public class DemCondLab implements Serializable {

    private static final long serialVersionUID = 6064094520930518391L;
    private Long id;
    private BneActEconomica bneActEconomica;
    private BneSitLaboral bneSitLaboral;
    private PerPersonas perPersonas;
    private Date fecIni;
    private BigDecimal salario;
    private Date fecAlta;
    private Date fecModif;

    private Boolean buscandoEmpleo;

    //en tabla pero no usados aca
//  private BneRelacionesContractuales bneRelacionesContractuales;
//  private BneTiposJornada bneTiposJornada;
//    private Integer numTrab;
//  private Long duracionContrato;
    public DemCondLab() {
    }

    public DemCondLab(final Long id) {
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
    @JoinColumn(name = "ID_ACT_ECONOMICA")
    public BneActEconomica getBneActEconomica() {
        return this.bneActEconomica;
    }

    public void setBneActEconomica(final BneActEconomica bneActEconomica) {
        this.bneActEconomica = bneActEconomica;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SIT_LABORAL")
    public BneSitLaboral getBneSitLaboral() {
        return this.bneSitLaboral;
    }

    public void setBneSitLaboral(final BneSitLaboral bneSitLaboral) {
        this.bneSitLaboral = bneSitLaboral;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    public PerPersonas getPerPersonas() {
        return this.perPersonas;
    }

    public void setPerPersonas(final PerPersonas perPersonas) {
        this.perPersonas = perPersonas;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_INI", length = 10)
    public Date getFecIni() {
        return this.fecIni;
    }

    public void setFecIni(final Date fecIni) {
        this.fecIni = fecIni;
    }

    @Column(name = "SALARIO", precision = 18)
    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setSalario(final BigDecimal salario) {
        this.salario = salario;
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

    @Transient
    public Serializable getPrimaryKey() {
        return this.id;
    }

    @Column(name = "BUSCANDO_EMPLEO")
    public Boolean getBuscandoEmpleo() {
        return this.buscandoEmpleo;
    }

    public void setBuscandoEmpleo(final Boolean buscandoEmpleo) {
        this.buscandoEmpleo = buscandoEmpleo;
    }

}
