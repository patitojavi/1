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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "DEM_EXP_LABORAL")
public class DemExpLaboral implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BneNivelProfesional bneNivelProfesional;
    private BneOcupaciones bneOcupaciones;
    private BneRegiones bneRegiones;
    private Boolean fueraChile;
    private PerPersonas perPersonas;
    private String razonSocial;
    private Date fecIni;
    private Date fecFin;
    private Integer numMeses;
    private BigDecimal sueldo;
    private String descripcion;
    private Long codFichero;
    private Date fecAlta;
    private Date fecModif;
    private String referencias;
    private DemExpLaboralArchivos demExpLaboralArchivos;
    private String otrasOcupaciones;

    public DemExpLaboral() {
    }

    public DemExpLaboral(final Long id) {
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
    @JoinColumn(name = "ID_NIV_PROFESIONAL")
    public BneNivelProfesional getBneNivelProfesional() {
        return this.bneNivelProfesional;
    }

    public void setBneNivelProfesional(final BneNivelProfesional bneNivelProfesional) {
        this.bneNivelProfesional = bneNivelProfesional;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OCUPACION", nullable = false)
    public BneOcupaciones getBneOcupaciones() {
        return this.bneOcupaciones;
    }

    public void setBneOcupaciones(final BneOcupaciones bneOcupaciones) {
        this.bneOcupaciones = bneOcupaciones;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REGION")
    public BneRegiones getBneRegiones() {
        return this.bneRegiones;
    }

    public void setBneRegiones(final BneRegiones bneRegiones) {
        this.bneRegiones = bneRegiones;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    public PerPersonas getPerPersonas() {
        return this.perPersonas;
    }

    public void setPerPersonas(final PerPersonas perPersonas) {
        this.perPersonas = perPersonas;
    }

    @Column(name = "RAZON_SOCIAL", length = 100)
    public String getRazonSocial() {
        return this.razonSocial;
    }

    public void setRazonSocial(final String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_INI", length = 10)
    public Date getFecIni() {
        return this.fecIni;
    }

    public void setFecIni(final Date fecIni) {
        this.fecIni = fecIni;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_FIN", length = 10)
    public Date getFecFin() {
        return this.fecFin;
    }

    public void setFecFin(final Date fecFin) {
        this.fecFin = fecFin;
    }

    @Column(name = "NUM_MESES")
    public Integer getNumMeses() {
        return this.numMeses;
    }

    public void setNumMeses(final Integer numMeses) {
        this.numMeses = numMeses;
    }

    @Column(name = "SUELDO", precision = 18)
    public BigDecimal getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(final BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    @Column(name = "DESCRIPCION", length = 200)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "COD_FICHERO", precision = 18, scale = 0)
    public Long getCodFichero() {
        return this.codFichero;
    }

    public void setCodFichero(final Long codFichero) {
        this.codFichero = codFichero;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_ALTA", nullable = false)
    public Date getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIF", nullable = false)
    public Date getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Date fecModif) {
        this.fecModif = fecModif;
    }

    @Column(name = "REFERENCIAS", length = 40)
    public String getReferencias() {
        return this.referencias;
    }

    @Column(name = "FUERA_CHILE")
    public Boolean getFueraChile() {
        return this.fueraChile;
    }

    public void setFueraChile(final Boolean fueraChile) {
        this.fueraChile = fueraChile;
    }

    public void setReferencias(final String referencias) {
        this.referencias = referencias;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "demExpLaboral")
    public DemExpLaboralArchivos getDemExpLaboralArchivos() {
        return this.demExpLaboralArchivos;
    }

    public void setDemExpLaboralArchivos(final DemExpLaboralArchivos demExpLaboralArchivos) {
        this.demExpLaboralArchivos = demExpLaboralArchivos;
    }

    @Column(name = "OTRAS_OCUPACIONES", length = 500)
    public String getOtrasOcupaciones() {
        return this.otrasOcupaciones;
    }

    public void setOtrasOcupaciones(final String otrasOcupaciones) {
        this.otrasOcupaciones = otrasOcupaciones;
    }

}
