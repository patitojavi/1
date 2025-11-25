package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemHabilidadesDTO implements Serializable {

    private static final long serialVersionUID = -5594945228201817073L;
    private Long id;
    private String habilidad;
    private PerPersonaDTO perPersona;
    private Date fecAlta;
    private Date fecModif;

    public DemHabilidadesDTO() {
    }

    public DemHabilidadesDTO(Long id, String habilidad, PerPersonaDTO perPersona, Date fecAlta, Date fecModif) {
        super();
        this.id = id;
        this.habilidad = habilidad;
        this.perPersona = perPersona;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
    }

}
