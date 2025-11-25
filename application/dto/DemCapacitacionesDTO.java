package es.altia.bne.postulante.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class DemCapacitacionesDTO implements java.io.Serializable {

    private static final long serialVersionUID = -9052972925948869328L;
    private Long id;
//    private CapCapacitacionesDto capCapacitaciones;
    private PerPersonaDTO perPersona;
    private String institucion;
    private Long numHoras;
    private Long codFichero;

    private Date fecAlta;

    private Date fecModif;
    private Boolean validado;
    private Boolean visible;
    private Date fecIni;
    private Date fecFin;
    private String nomCapacitacion;
    private DemCapacitacionesArchivosDTO demCapacitacionesArchivos;
    private Boolean modifica;

    // Campos solo para los cursos de SENCE
    private String empresa;
    private String programa;
    private BigDecimal porcentajeHorasAsistidas;

    private Long idFederacion;
    private String idCurso;

    public DemCapacitacionesDTO() {
        super();
    }

    public DemCapacitacionesDTO(Long id, PerPersonaDTO perPersona, String institucion, Long numHoras, Long codFichero,
            Date fecAlta, Date fecModif, Boolean validado, Boolean visible, Date fecIni, Date fecFin,
            String nomCapacitacion, DemCapacitacionesArchivosDTO demCapacitacionesArchivos, Boolean modifica,
            String empresa, String programa, BigDecimal porcentajeHorasAsistidas, Long idFederacion, String idCurso) {
        super();
        this.id = id;
        this.perPersona = perPersona;
        this.institucion = institucion;
        this.numHoras = numHoras;
        this.codFichero = codFichero;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.validado = validado;
        this.visible = visible;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
        this.nomCapacitacion = nomCapacitacion;
        this.demCapacitacionesArchivos = demCapacitacionesArchivos;
        this.modifica = modifica;
        this.empresa = empresa;
        this.programa = programa;
        this.porcentajeHorasAsistidas = porcentajeHorasAsistidas;
        this.idFederacion = idFederacion;
        this.idCurso = idCurso;
    }

}
