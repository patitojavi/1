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
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "BNE_PAISES", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class BnePaises implements Serializable {

    private static final long serialVersionUID = -7515178616658527855L;

    private Integer id;

    private String nombre;

    private String codigo;

    private String codigoAfc;

    private Date fecIniVig;

    private Date fecFinVig;

    private Date fecAlta;

    private Date fecModif;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NOMBRE", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "CODIGO", unique = true, nullable = false, length = 3)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "CODIGO_AFC", length = 2)
    public String getCodigoAfc() {
        return codigoAfc;
    }

    public void setCodigoAfc(String codigoAfc) {
        this.codigoAfc = codigoAfc;
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
}
