package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "BNE_TIPO_EDUCACION_MEDIA")
public class BneTipoEducacionMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private Date fecIniVig;
    private Date fecFinVig;
    private Date fecAlta;
    private Date fecModif;

    public BneTipoEducacionMedia() {
    }

    public BneTipoEducacionMedia(final Integer id) {
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
    @Column(name = "FEC_ALTA", nullable = false, length = 23)
    public Date getFecAlta() {
        return this.fecAlta;
    }

    public void setFecAlta(final Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIF", nullable = false, length = 23)
    public Date getFecModif() {
        return this.fecModif;
    }

    public void setFecModif(final Date fecModif) {
        this.fecModif = fecModif;
    }
}
