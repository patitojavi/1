package es.altia.bne.postulante.application.dto;

import java.util.Date;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneComunas;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneEstadosValidacionRC;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.NivelesEducativos;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerClave;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PerPersonaDTO {

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long id;
    private String numDocumento;
    private String nombre;
    private String ape1;
    private String ape2;
    private Date fecNac;
    private String sexo;
    private Integer nacionalidad;
    private Integer estadoCivil;
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
    private Integer estadoValidRC; // nuevo
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

    public PerPersonaDTO(Long id, Integer tipoDocumento, String numDocumento, String nombre, String ape1, String ape2,
            String sexo, Date fecNac, Integer nacionalidad, Integer estadoCivil, String tlfnoNotif, String tlfnoAlt,
            Boolean notifSms, String email, Boolean indMostrarDatospostulante) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.sexo = sexo;
        this.fecNac = fecNac;
        this.nacionalidad = nacionalidad;
        this.estadoCivil = estadoCivil;
        this.tlfnoNotif = tlfnoNotif;
        this.tlfnoAlt = tlfnoAlt;
        this.notifSms = notifSms;
        this.email = email;
        this.indMostrarDatospostulante = indMostrarDatospostulante;
    }
}
