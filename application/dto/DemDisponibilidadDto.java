package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemDisponibilidadDto implements Serializable {

    private static final long serialVersionUID = -8277727128910535192L;
    private Long id;
    private Integer idBneTiposJornada;
    private String nombreBneTiposJornada;
    private PerPersonaDTO perPersona;
    private Date fecIni;
    private Date fecFin;
    private String nocturno;
    private String soloManana;
    private String especiales;
    private String soloTarde;
    private String festivos;
    private Boolean checkNocturno;
    private Boolean checkSoloManana;
    private Boolean checkEspeciales;
    private Boolean checkSoloTarde;
    private Boolean checkFestivos;
    private Date fecAlta;
    private Date fecModif;

    public DemDisponibilidadDto() {
        super();
    }

    public DemDisponibilidadDto(Long id, Integer idBneTiposJornada, String nombreBneTiposJornada,
            PerPersonaDTO perPersona, Date fecIni, Date fecFin, String nocturno, String soloManana, String especiales,
            String soloTarde, String festivos, Boolean checkNocturno, Boolean checkSoloManana, Boolean checkEspeciales,
            Boolean checkSoloTarde, Boolean checkFestivos, Date fecAlta, Date fecModif) {
        super();
        this.id = id;
        this.idBneTiposJornada = idBneTiposJornada;
        this.nombreBneTiposJornada = nombreBneTiposJornada;
        this.perPersona = perPersona;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
        this.nocturno = nocturno;
        this.soloManana = soloManana;
        this.especiales = especiales;
        this.soloTarde = soloTarde;
        this.festivos = festivos;
        this.checkNocturno = checkNocturno;
        this.checkSoloManana = checkSoloManana;
        this.checkEspeciales = checkEspeciales;
        this.checkSoloTarde = checkSoloTarde;
        this.checkFestivos = checkFestivos;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
    }

}
