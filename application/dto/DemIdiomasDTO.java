package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DemIdiomasDTO implements Serializable {

    private static final long serialVersionUID = 3698458755079489342L;
    private Long id;
    private Integer idBneIdiomas;
    private String nombreBneIdiomas;
    private Integer idBneNivelesIdiomasByIdNivHablado;
    private String nombreBneNivelesIdiomasByIdNivHablado;
    private Integer idBneNivelesIdiomasByIdNivEscritura;
    private String nombreBneNivelesIdiomasByIdNivEscritura;
    private Integer idBneNivelesIdiomasByIdNivLectura;
    private String nombreBneNivelesIdiomasByIdNivLectura;
    private PerPersonaDTO perPersona;
    private String interpretacion;
    private String traduccion;
    private String docente;
    private String materno;
    private Long codFichero;
    private Date fecAlta;
    private Date fecModif;
    private DemIdiomasArchivosDTO demIdiomasArchivos;
    private List<DemIdiomasTitulacionesDTO> titulacionesIdiomas;

    private Boolean modifica;

    public DemIdiomasDTO() {
    }

    public DemIdiomasDTO(Long id, Integer idBneIdiomas, String nombreBneIdiomas,
            Integer idBneNivelesIdiomasByIdNivHablado, String nombreBneNivelesIdiomasByIdNivHablado,
            Integer idBneNivelesIdiomasByIdNivEscritura, String nombreBneNivelesIdiomasByIdNivEscritura,
            Integer idBneNivelesIdiomasByIdNivLectura, String nombreBneNivelesIdiomasByIdNivLectura,
            PerPersonaDTO perPersona, String interpretacion, String traduccion, String docente, String materno,
            Long codFichero, Date fecAlta, Date fecModif, DemIdiomasArchivosDTO demIdiomasArchivos,
            List<DemIdiomasTitulacionesDTO> titulacionesIdiomas, Boolean modifica) {
        super();
        this.id = id;
        this.idBneIdiomas = idBneIdiomas;
        this.nombreBneIdiomas = nombreBneIdiomas;
        this.idBneNivelesIdiomasByIdNivHablado = idBneNivelesIdiomasByIdNivHablado;
        this.nombreBneNivelesIdiomasByIdNivHablado = nombreBneNivelesIdiomasByIdNivHablado;
        this.idBneNivelesIdiomasByIdNivEscritura = idBneNivelesIdiomasByIdNivEscritura;
        this.nombreBneNivelesIdiomasByIdNivEscritura = nombreBneNivelesIdiomasByIdNivEscritura;
        this.idBneNivelesIdiomasByIdNivLectura = idBneNivelesIdiomasByIdNivLectura;
        this.nombreBneNivelesIdiomasByIdNivLectura = nombreBneNivelesIdiomasByIdNivLectura;
        this.perPersona = perPersona;
        this.interpretacion = interpretacion;
        this.traduccion = traduccion;
        this.docente = docente;
        this.materno = materno;
        this.codFichero = codFichero;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.demIdiomasArchivos = demIdiomasArchivos;
        this.titulacionesIdiomas = titulacionesIdiomas;
        this.modifica = modifica;
    }

}
