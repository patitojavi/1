package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.util.Date;

import javax.annotation.Nullable;

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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "PER_PERSONAS", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "TIPO_DOCUMENTO", "DIGITO_VERIFICADOR", "NUM_DOCUMENTO" }) })
public class PerPersonas {

    private Long id;
    private String numDocumento;
    private String nombre;
    private String ape1;
    private String ape2;
    private Date fecNac;
    private String sexo;
    private Integer idNacionalidad;
    private Integer idEstadoCivil;
    private String tlfnoNotif;
    private String tlfnoAlt;
    private String email;
//    private Integer region;//mapeado como objeto
//    private Integer comuna;//mapeado como objeto
    private String indDiscapacidad;
    private String ajustesPtoTrab;
    private Long ultRemDevengada;
    private Date fecActAfc;
    private Date fecActOmil;
    private String estado;
    private String preguntaSitio;
    private Date fecAlta;
    private Date fecModif;
    private String dirExtra;
    private String digitoVerificador;
    private Integer tipoDocumento;
    private String calle;
    private String numero;
    private String casa;
    private String poblacion;
    private Boolean aceptaCondiciones;
    private Boolean notifSms;
//  private Integer nivelEducativo;//mapeado como objeto
    private Boolean esFcs;
    private Boolean cvCompleto;
    private Boolean indPeriodoCertificacion;
    private Integer sitio; // nuevo
    private Boolean fcsAnterior;
    private Integer omilReferencia; // nuevo
    private String motivoOmilReferencia; // nuevo
    private Date fecModifCv;
    private Date fecModifPersona;
 //   private Integer estadoValidRC;//mapeado como objeto
    private Integer usuarioIntermediador; // nuevo
    private String nic;
    private String nicDv;
    private Integer disponibilidad; // nuevo
    private Boolean indMostrarDatospostulante; // nuevo
    private Boolean indMostrarDiccion; // nuevo
    private Date fecModifDiscapacidad; // nuevo
    private Boolean mostrarDiscapacidad; // nuevo

    private BneEstadosValidacionRC bneEstadosValidacionRC;
    private PerClave perClave;

    private BneComunas bneComunas;
    private NivelesEducativos bneNivelesEducativos;
    private BneRegiones bneRegiones;

    public PerPersonas() {
    }

    public PerPersonas(final Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMUNA")
    public BneComunas getBneComunas() {
        return this.bneComunas;
    }

    public void setBneComunas(final BneComunas bneComunas) {
        this.bneComunas = bneComunas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_NIV_EDUCATIVO")
    public NivelesEducativos getBneNivelesEducativos() {
        return this.bneNivelesEducativos;
    }

    public void setBneNivelesEducativos(final NivelesEducativos bneNivelesEducativos) {
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

    @Column(name = "NUM_DOCUMENTO", unique = true, nullable = false, length = 10)
    public String getNumDocumento() {
        return this.numDocumento;
    }

    public void setNumDocumento(final String numDocumento) {
        this.numDocumento = numDocumento;
    }

    @Column(name = "NOMBRE", nullable = false, length = 100)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "APE1", nullable = false, length = 100)
    public String getApe1() {
        return this.ape1;
    }

    public void setApe1(final String ape1) {
        this.ape1 = ape1;
    }

    @Column(name = "APE2", length = 100)
    public String getApe2() {
        return this.ape2;
    }

    public void setApe2(final String ape2) {
        this.ape2 = ape2;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_NAC", length = 10)
    public Date getFecNac() {
        return this.fecNac;
    }

    public void setFecNac(final Date fecNac) {
        this.fecNac = fecNac;
    }

    @Column(name = "SEXO", length = 1)
    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "TLFNO_NOTIF", length = 15)
    public String getTlfnoNotif() {
        return this.tlfnoNotif;
    }

    public void setTlfnoNotif(final String tlfnoNotif) {
        this.tlfnoNotif = tlfnoNotif;
    }

    @Column(name = "TLFNO_ALT", length = 15)
    public String getTlfnoAlt() {
        return this.tlfnoAlt;
    }

    public void setTlfnoAlt(final String tlfnoAlt) {
        this.tlfnoAlt = tlfnoAlt;
    }

    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "IND_DISCAPACIDAD", length = 1)
    public String getIndDiscapacidad() {
        return this.indDiscapacidad;
    }

    public void setIndDiscapacidad(final String indDiscapacidad) {
        this.indDiscapacidad = indDiscapacidad;
    }

    @Column(name = "AJUSTES_PTO_TRAB", length = 1)
    public String getAjustesPtoTrab() {
        return this.ajustesPtoTrab;
    }

    public void setAjustesPtoTrab(final String ajustesPtoTrab) {
        this.ajustesPtoTrab = ajustesPtoTrab;
    }

    @Column(name = "ULT_REM_DEVENGADA", precision = 18, scale = 0)
    public Long getUltRemDevengada() {
        return this.ultRemDevengada;
    }

    public void setUltRemDevengada(final Long ultRemDevengada) {
        this.ultRemDevengada = ultRemDevengada;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_ACT_AFC", length = 10)
    public Date getFecActAfc() {
        return this.fecActAfc;
    }

    public void setFecActAfc(final Date fecActAfc) {
        this.fecActAfc = fecActAfc;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_ACT_OMIL", length = 10)
    public Date getFecActOmil() {
        return this.fecActOmil;
    }

    public void setFecActOmil(final Date fecActOmil) {
        this.fecActOmil = fecActOmil;
    }

    @Column(name = "ESTADO", length = 1)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    @Column(name = "PREGUNTA_SITIO", length = 500)
    public String getPreguntaSitio() {
        return this.preguntaSitio;
    }

    public void setPreguntaSitio(final String preguntaSitio) {
        this.preguntaSitio = preguntaSitio;
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

    @Column(name = "DIR_EXTRA", length = 100)
    public String getDirExtra() {
        return this.dirExtra;
    }

    public void setDirExtra(final String dirExtra) {
        this.dirExtra = dirExtra;
    }

    @Column(name = "DIGITO_VERIFICADOR", length = 1)
    public String getDigitoVerificador() {
        return this.digitoVerificador;
    }

    public void setDigitoVerificador(final String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    @Column(name = "TIPO_DOCUMENTO", nullable = false)
    public Integer getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(final Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Column(name = "CALLE", length = 100)
    public String getCalle() {
        return this.calle;
    }

    public void setCalle(final String calle) {
        this.calle = calle;
    }

    @Column(name = "NUMERO", length = 10)
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

    @Column(name = "CASA", length = 50)
    public String getCasa() {
        return this.casa;
    }

    public void setCasa(final String casa) {
        this.casa = casa;
    }

    @Column(name = "POBLACION", length = 100)
    public String getPoblacion() {
        return this.poblacion;
    }

    public void setPoblacion(final String poblacion) {
        this.poblacion = poblacion;
    }

    @Column(name = "ACEPTA_CONDICIONES")
    public Boolean getAceptaCondiciones() {
        return this.aceptaCondiciones;
    }

    public void setAceptaCondiciones(final Boolean aceptaCondiciones) {
        this.aceptaCondiciones = aceptaCondiciones;
    }

    @Column(name = "NOTIF_SMS")
    public Boolean getNotifSms() {
        return this.notifSms;
    }

    public void setNotifSms(final Boolean notifSms) {
        this.notifSms = notifSms;
    }

    @Column(name = "ES_FCS")
    public Boolean getEsFcs() {
        return this.esFcs;
    }

    public void setEsFcs(final Boolean esFcs) {
        this.esFcs = esFcs;
    }

    @Column(name = "FCS_ANTERIOR")
    public Boolean getFcsAnterior() {
        return this.fcsAnterior;
    }

    public void setFcsAnterior(final Boolean fcsAnterior) {
        this.fcsAnterior = fcsAnterior;
    }

    @Column(name = "CV_COMPLETO")
    public Boolean getCvCompleto() {
        return this.cvCompleto;
    }

    public void setCvCompleto(final Boolean cvCompleto) {
        this.cvCompleto = cvCompleto;
    }

    @Column(name = "IND_PERIODO_CERTIFICACION")
    public Boolean getIndPeriodoCertificacion() {
        return this.indPeriodoCertificacion;
    }

    public void setIndPeriodoCertificacion(final Boolean indPeriodoCertificacion) {
        this.indPeriodoCertificacion = indPeriodoCertificacion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_VALID_RC")
    public BneEstadosValidacionRC getBneEstadosValidacionRC() {
        return this.bneEstadosValidacionRC;
    }

    public void setBneEstadosValidacionRC(final BneEstadosValidacionRC bneEstadosValidacionRC) {
        this.bneEstadosValidacionRC = bneEstadosValidacionRC;
    }

    /**
     * Devuelve la fecha de la última actualización del CV del postulante.
     *
     * @return un {@link Date} con la fecha de la última actualización del CV, o
     *         <code>null</code> si no se han registrado actualizaciones del CV
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_MODIF_CV", nullable = true)
    public @Nullable Date getFecModifCv() {
        return this.fecModifCv != null ? new Date(this.fecModifCv.getTime()) : null;
    }

    /**
     * Asigna la fecha de la última actualización al CV de un postulante.
     *
     * @param fecModifCv un {@link Date} con la fecha de la última actualización del
     *                   CV
     */
    public void setFecModifCv(final @Nullable Date fecModifCv) {
        this.fecModifCv = fecModifCv;
    }

    /**
     * Devuelve la fecha de la última actualización de un postulante.
     *
     * @return un {@link Date} con la fecha de la última actualización del
     *         postulante, o <code>null</code> si no se han registrado
     *         actualizaciones de la persona
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FEC_MODIF_PERSONA", nullable = true)
    public @Nullable Date getFecModifPersona() {
        return this.fecModifPersona != null ? new Date(this.fecModifPersona.getTime()) : null;
    }

    @Column(name = "NIC", nullable = true)
    public String getNic() {
        return this.nic;
    }

    public void setNic(final String nic) {
        this.nic = nic;
    }

    @Column(name = "NIC_DV", nullable = true)
    public String getNicDv() {
        return this.nicDv;
    }

    public void setNicDv(final String nicDv) {
        this.nicDv = nicDv;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "perPersonas")
    public PerClave getPerClave() {
        return this.perClave;
    }

    public void setPerClave(final PerClave perClave) {
        this.perClave = perClave;
    }

    /**
     * Asigna la fecha de la última actualización del postulante.
     *
     * @param fecModifPersona un {@link Date} con la fecha de la última
     *                        actualización de la persona
     */
    public void setFecModifPersona(final @Nullable Date fecModifPersona) {
        this.fecModifPersona = fecModifPersona;
    }

    @Column(name = "ID_SITIO")
    public Integer getSitio() {
        return sitio;
    }

    public void setSitio(Integer sitio) {
        this.sitio = sitio;
    }

    @Column(name = "OMIL_REFERENCIA")
    public Integer getOmilReferencia() {
        return omilReferencia;
    }

    public void setOmilReferencia(Integer omilReferencia) {
        this.omilReferencia = omilReferencia;
    }

    @Column(name = "MOTIVO_OMIL_REFERENCIA")
    public String getMotivoOmilReferencia() {
        return motivoOmilReferencia;
    }

    public void setMotivoOmilReferencia(String motivoOmilReferencia) {
        this.motivoOmilReferencia = motivoOmilReferencia;
    }

//    @Column(name = "ID_ESTADO_VALID_RC")
//    public Integer getEstadoValidRC() {
//        return estadoValidRC;
//    }
//
//    public void setEstadoValidRC(Integer estadoValidRC) {
//        this.estadoValidRC = estadoValidRC;
//    }

    @Column(name = "ID_USUARIO_INTERMEDIADOR")
    public Integer getUsuarioIntermediador() {
        return usuarioIntermediador;
    }

    public void setUsuarioIntermediador(Integer usuarioIntermediador) {
        this.usuarioIntermediador = usuarioIntermediador;
    }

    @Column(name = "ID_DISPONIBILIDAD")
    public Integer getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Column(name = "IND_MOSTRAR_DATOS_POSTULANTES")
    public Boolean getIndMostrarDatospostulante() {
        return indMostrarDatospostulante;
    }

    public void setIndMostrarDatospostulante(Boolean indMostrarDatospostulante) {
        this.indMostrarDatospostulante = indMostrarDatospostulante;
    }

    @Column(name = "IND_MOSTRAR_DICCION")
    public Boolean getIndMostrarDiccion() {
        return indMostrarDiccion;
    }

    public void setIndMostrarDiccion(Boolean indMostrarDiccion) {
        this.indMostrarDiccion = indMostrarDiccion;
    }

    @Column(name = "FEC_MODIF_DISCAPACIDAD")
    public Date getFecModifDiscapacidad() {
        return fecModifDiscapacidad;
    }

    public void setFecModifDiscapacidad(Date fecModifDiscapacidad) {
        this.fecModifDiscapacidad = fecModifDiscapacidad;
    }

    @Column(name = "MOSTRAR_DISCAPACIDAD")
    public Boolean getMostrarDiscapacidad() {
        return mostrarDiscapacidad;
    }

    public void setMostrarDiscapacidad(Boolean mostrarDiscapacidad) {
        this.mostrarDiscapacidad = mostrarDiscapacidad;
    }

    @Column(name = "ID_NACIONALIDAD", nullable = false, length = 100)
    public Integer getIdNacionalidad() {
        return this.idNacionalidad;
    }

    public void setIdNacionalidad(final Integer idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    @Column(name = "ID_ESTADO_CIVIL", nullable = false, length = 100)
    public Integer getIdEstadoCivil() {
        return this.idEstadoCivil;
    }

    public void setIdEstadoCivil(final Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    @Override
    public String toString() {
        return "PerPersonas [id=" + id + ", numDocumento=" + numDocumento + ", nombre=" + nombre + ", ape1=" + ape1
                + ", ape2=" + ape2 + ", fecNac=" + fecNac + ", sexo=" + sexo + ", idNacionalidad=" + idNacionalidad
                + ", idEstadoCivil=" + idEstadoCivil + ", tlfnoNotif=" + tlfnoNotif + ", tlfnoAlt=" + tlfnoAlt
                + ", email=" + email + ", indDiscapacidad=" + indDiscapacidad + ", ajustesPtoTrab=" + ajustesPtoTrab
                + ", ultRemDevengada=" + ultRemDevengada + ", fecActAfc=" + fecActAfc + ", fecActOmil=" + fecActOmil
                + ", estado=" + estado + ", preguntaSitio=" + preguntaSitio + ", fecAlta=" + fecAlta + ", fecModif="
                + fecModif + ", dirExtra=" + dirExtra + ", digitoVerificador=" + digitoVerificador + ", tipoDocumento="
                + tipoDocumento + ", calle=" + calle + ", numero=" + numero + ", casa=" + casa + ", poblacion="
                + poblacion + ", aceptaCondiciones=" + aceptaCondiciones + ", notifSms=" + notifSms + ", esFcs=" + esFcs
                + ", cvCompleto=" + cvCompleto + ", indPeriodoCertificacion=" + indPeriodoCertificacion + ", sitio="
                + sitio + ", fcsAnterior=" + fcsAnterior + ", omilReferencia=" + omilReferencia
                + ", motivoOmilReferencia=" + motivoOmilReferencia + ", fecModifCv=" + fecModifCv + ", fecModifPersona="
                + fecModifPersona + ", usuarioIntermediador=" + usuarioIntermediador + ", nic=" + nic + ", nicDv="
                + nicDv + ", disponibilidad=" + disponibilidad + ", indMostrarDatospostulante="
                + indMostrarDatospostulante + ", indMostrarDiccion=" + indMostrarDiccion + ", fecModifDiscapacidad="
                + fecModifDiscapacidad + ", mostrarDiscapacidad=" + mostrarDiscapacidad + ", bneEstadosValidacionRC="
                + bneEstadosValidacionRC + ", perClave=" + perClave + ", bneComunas=" + bneComunas
                + ", bneNivelesEducativos=" + bneNivelesEducativos + ", bneRegiones=" + bneRegiones + "]";
    }
}
