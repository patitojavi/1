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
@Table(name = "DEM_TITULACIONES")
public class DemTitulaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BneAreas bneAreas;
    private BneNivelesEducativos bneNivelesEducativos;
    private BneRegiones bneRegiones;
    private BneTipoEducacionMedia bneTipoEducacionMedia;
    private BneTitulacionesUniv bneTitulacionesUniv;
    private PerPersonas perPersonas;
    private Date fecFin;
    private Integer ultCursoAprob;
    private String titulacionNoExistente;
    private Date fecAlta;
    private Date fecModif;
    private String observaciones;
    private String titulacionMagisDoctor;
    private String otrasTitulaciones;
    private String especialidad;
    private String otros;
    private String nombreInst;

    public DemTitulaciones() {
    }

    public DemTitulaciones(final Long id) {
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
    @JoinColumn(name = "ID_AREA")
    public BneAreas getBneAreas() {
        return this.bneAreas;
    }

    public void setBneAreas(final BneAreas bneAreas) {
        this.bneAreas = bneAreas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_NIVEL_EDUCATIVO")
    public BneNivelesEducativos getBneNivelesEducativos() {
        return this.bneNivelesEducativos;
    }

    public void setBneNivelesEducativos(final BneNivelesEducativos bneNivelesEducativos) {
        this.bneNivelesEducativos = bneNivelesEducativos;
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
    @JoinColumn(name = "ID_TIPO_EDUC_MEDIA")
    public BneTipoEducacionMedia getBneTipoEducacionMedia() {
        return this.bneTipoEducacionMedia;
    }

    public void setBneTipoEducacionMedia(final BneTipoEducacionMedia bneTipoEducacionMedia) {
        this.bneTipoEducacionMedia = bneTipoEducacionMedia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TITULACION")
    public BneTitulacionesUniv getBneTitulacionesUniv() {
        return this.bneTitulacionesUniv;
    }

    public void setBneTitulacionesUniv(final BneTitulacionesUniv bneTitulacionesUniv) {
        this.bneTitulacionesUniv = bneTitulacionesUniv;
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
    @Column(name = "FEC_FIN", length = 10)
    public Date getFecFin() {
        return this.fecFin;
    }

    public void setFecFin(final Date fecFin) {
        this.fecFin = fecFin;
    }

    @Column(name = "ULT_CURSO_APROB")
    public Integer getUltCursoAprob() {
        return this.ultCursoAprob;
    }

    public void setUltCursoAprob(final Integer ultCursoAprob) {
        this.ultCursoAprob = ultCursoAprob;
    }

    @Column(name = "TITULACION_NO_EXISTENTE", length = 500)
    public String getTitulacionNoExistente() {
        return this.titulacionNoExistente;
    }

    public void setTitulacionNoExistente(final String titulacionNoExistente) {
        this.titulacionNoExistente = titulacionNoExistente;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "FEC_ALTA", nullable = false, length = 23)
    public Date getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "FEC_MODIF", nullable = false, length = 23)
    public Date getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Date fecModif) {
        this.fecModif = fecModif;
    }

    @Column(name = "OBSERVACIONES", length = 500)
    public String getObservaciones() {
        return this.observaciones;
    }

    @Column(name = "TITULACION_MAGIS_DOCTOR", length = 100)
    public String getTitulacionMagisDoctor() {
        return this.titulacionMagisDoctor;
    }

    public void setTitulacionMagisDoctor(final String titulacionMagisDoctor) {
        this.titulacionMagisDoctor = titulacionMagisDoctor;
    }

    public void setObservaciones(final String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "OTRAS_TITULACIONES", length = 500)
    public String getOtrasTitulaciones() {
        return this.otrasTitulaciones;
    }

    public void setOtrasTitulaciones(final String otrasTitulaciones) {
        this.otrasTitulaciones = otrasTitulaciones;
    }

    @Column(name = "ESPECIALIDAD", length = 100)
    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(final String especialidad) {
        this.especialidad = especialidad;
    }

    @Column(name = "OTROS", length = 100)
    public String getOtros() {
        return this.otros;
    }

    public void setOtros(final String otros) {
        this.otros = otros;
    }

    @Column(name = "NOMBRE_INSTITUCION", length = 30)
    public String getNombreInst() {
        return this.nombreInst;
    }

    public void setNombreInst(final String nombreInst) {
        this.nombreInst = nombreInst;
    }

}
