package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemGruposEmpleoDTO implements Serializable {

    private static final long serialVersionUID = 5321594055683016613L;
    private Integer id;
    private PerPersonaDTO perPersona;
    private Integer idBneGruposEmpleo;
    private String nombreBneGruposEmpleo;
    private boolean pertenece;
    private Date fecAlta;
    private Date fecModif;

    public DemGruposEmpleoDTO() {

    }

    public DemGruposEmpleoDTO(Integer id, PerPersonaDTO perPersona, Integer idBneGruposEmpleo,
            String nombreBneGruposEmpleo, boolean pertenece, Date fecAlta, Date fecModif) {
        super();
        this.id = id;
        this.perPersona = perPersona;
        this.idBneGruposEmpleo = idBneGruposEmpleo;
        this.nombreBneGruposEmpleo = nombreBneGruposEmpleo;
        this.pertenece = pertenece;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
    }

}
