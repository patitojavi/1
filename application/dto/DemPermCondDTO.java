package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemPermCondDTO implements Serializable {

    private static final long serialVersionUID = -2839385324278986835L;
    private Long id;
    private Integer idBnePermCond;
    private String nombreBnePermCond;
    private PerPersonaDTO perPersona;
    private Date fecAlta;
    private Date fecModif;
    private Boolean modifica;

    public DemPermCondDTO() {
    }

    public DemPermCondDTO(Long id, Integer idBnePermCond, String nombreBnePermCond, PerPersonaDTO perPersona,
            Date fecAlta, Date fecModif, Boolean modifica) {
        super();
        this.id = id;
        this.idBnePermCond = idBnePermCond;
        this.nombreBnePermCond = nombreBnePermCond;
        this.perPersona = perPersona;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.modifica = modifica;
    }

 
}
