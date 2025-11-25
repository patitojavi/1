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
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "BNE_OCUPACIONES", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class BneOcupaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    /* OJO Mantener esta propiedad con el nombre correcto */
//    private final String PROPIEDAD_FIN_VIGENCIA = "fecFinVig";

    private Integer id;
    private String codigo;
    private String nombre;
    private String grupo1;
    private String grupo2;
    private String grupo3;
    private String grupo4;
    private Date fecIniVig;
    private Date fecFinVig;
    private Date fecAlta;
    private Date fecModif;
    private Integer orden;
    private Boolean cchc;

    public BneOcupaciones() {
    }

    public BneOcupaciones(final Integer id) {
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

    @Column(name = "CODIGO", unique = true, nullable = false, length = 8)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 200)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "GRUPO_1", length = 1)
    public String getGrupo1() {
        return this.grupo1;
    }

    public void setGrupo1(final String grupo1) {
        this.grupo1 = grupo1;
    }

    @Column(name = "GRUPO_2", length = 2)
    public String getGrupo2() {
        return this.grupo2;
    }

    public void setGrupo2(final String grupo2) {
        this.grupo2 = grupo2;
    }

    @Column(name = "GRUPO_3", length = 3)
    public String getGrupo3() {
        return this.grupo3;
    }

    public void setGrupo3(final String grupo3) {
        this.grupo3 = grupo3;
    }

    @Column(name = "GRUPO_4", length = 4)
    public String getGrupo4() {
        return this.grupo4;
    }

    public void setGrupo4(final String grupo4) {
        this.grupo4 = grupo4;
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

    @Column(name = "ORDEN")
    public Integer getOrden() {
        return this.orden;
    }

    public void setOrden(final Integer orden) {
        this.orden = orden;
    }

    @Column(name = "CCHC")
    public Boolean getCchc() {
        return this.cchc;
    }

    public void setCchc(final Boolean cchc) {
        this.cchc = cchc;
    }


//    @Override
//    public String propiedadFinVigencia() {
//        return this.PROPIEDAD_FIN_VIGENCIA;
//    }

}
