package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.io.Serializable;
import java.util.Date;

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
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "BNE_COMUNAS", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class BneComunas implements Serializable {

    private static final long serialVersionUID = -9072112397648698689L;

    private Integer id;

    private BneRegiones bneRegiones;

    private BneAreasMetropolitanas bneAreasMetropolitanas;

    private String codigo;

    private String nombre;

    private String codigoAfc;

    private String codigoSii;

    private Date fecIniVig;

    private Date fecFinVig;

    private Date fecAlta;

    private Date fecModif;

    private String codigoViejo;

    private String codOtrs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REGION", nullable = false)
    public BneRegiones getBneRegiones() {
        return bneRegiones;
    }

    public void setBneRegiones(BneRegiones bneRegiones) {
        this.bneRegiones = bneRegiones;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AREA_METROPOLITANA")
    public BneAreasMetropolitanas getBneAreasMetropolitanas() {
        return bneAreasMetropolitanas;
    }

    public void setBneAreasMetropolitanas(BneAreasMetropolitanas bneAreasMetropolitanas) {
        this.bneAreasMetropolitanas = bneAreasMetropolitanas;
    }

    @Column(name = "CODIGO", unique = true, nullable = false, length = 5)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "CODIGO_AFC", length = 8)
    public String getCodigoAfc() {
        return codigoAfc;
    }

    public void setCodigoAfc(String codigoAfc) {
        this.codigoAfc = codigoAfc;
    }

    @Column(name = "CODIGO_SII", length = 5)
    public String getCodigoSii() {
        return codigoSii;
    }

    public void setCodigoSii(String codigoSii) {
        this.codigoSii = codigoSii;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_INI_VIG", length = 10)
    public Date getFecIniVig() {
        return fecIniVig;
    }

    public void setFecIniVig(Date fecIniVig) {
        this.fecIniVig = fecIniVig;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_FIN_VIG", length = 10)
    public Date getFecFinVig() {
        return fecFinVig;
    }

    public void setFecFinVig(Date fecFinVig) {
        this.fecFinVig = fecFinVig;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_ALTA", nullable = false, length = 23)
    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_MODIF", nullable = false, length = 23)
    public Date getFecModif() {
        return fecModif;
    }

    public void setFecModif(Date fecModif) {
        this.fecModif = fecModif;
    }

    @Column(name = "CODIGO_VIEJO", length = 5)
    public String getCodigoViejo() {
        return codigoViejo;
    }

    public void setCodigoViejo(String codigoViejo) {
        this.codigoViejo = codigoViejo;
    }

    @Column(name = "COD_OTRS", length = 25)
    public String getCodOtrs() {
        return codOtrs;
    }

    public void setCodOtrs(String codOtrs) {
        this.codOtrs = codOtrs;
    }

    @Override
    public String toString() {
        return "BneComunas [id=" + id + ", bneRegiones=" + bneRegiones + ", bneAreasMetropolitanas="
                + bneAreasMetropolitanas + ", codigo=" + codigo + ", nombre=" + nombre + ", codigoAfc=" + codigoAfc
                + ", codigoSii=" + codigoSii + ", fecIniVig=" + fecIniVig + ", fecFinVig=" + fecFinVig + ", fecAlta="
                + fecAlta + ", fecModif=" + fecModif + ", codigoViejo=" + codigoViejo + ", codOtrs=" + codOtrs + "]";
    }
}
