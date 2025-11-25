package es.altia.bne.postulante.application.dto;
// Generated 28-sep-2016 14:03:17 by Hibernate Tools 4.3.5.Final

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemCertificacionesDTO implements Serializable {
    private static final long serialVersionUID = 2331466768641823476L;
    private Long id;
    private Integer idBneCertUcs;
    private String nombreBneCertUcs;
    private PerPersonaDTO perPersonas;
    private Boolean mostrarEnCv;
    private String documento;
    private Date fecAlta;
    private Date fecModif;
    private Boolean modifica;

    public DemCertificacionesDTO() {
    }

    public DemCertificacionesDTO(Long id, Integer idBneCertUcs, String nombreBneCertUcs, PerPersonaDTO perPersonas,
            Boolean mostrarEnCv, String documento, Date fecAlta, Date fecModif, Boolean modifica) {
        super();
        this.id = id;
        this.idBneCertUcs = idBneCertUcs;
        this.nombreBneCertUcs = nombreBneCertUcs;
        this.perPersonas = perPersonas;
        this.mostrarEnCv = mostrarEnCv;
        this.documento = documento;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.modifica = modifica;
    }

}
