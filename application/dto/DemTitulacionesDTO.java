package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DemTitulacionesDTO implements Serializable {
    private static final long serialVersionUID = -1205010154799090642L;
    private Long id;
    private Integer idBneAreas;
    private String nombreBneAreas;
    private Integer idBneNivelesEducativos;
    private String nombreBneNivelesEducativos;
    private Integer idBneRegiones;
    private String nombreBneRegiones;
    private Integer idBneTipoEducacionMedia;
    private String nombreBneTipoEducacionMedia;
    private Integer idBneTitulacionesUniv;
    private String nombreBneTitulacionesUniv;
    private Long idPostulante;

    @DateTimeFormat(pattern = "MM/yyyy")
    private Date fecFin;

    private Integer ultCursoAprob;
    private String titulacionNoExistente;
    private Date fecAlta;
    private Date fecModif;
    private String observaciones;
    private String titulacionMagisDoctor;
    private String otrasTitulaciones;
    private String especialidad;
    private String otros;
    private String nombreInst;

    public DemTitulacionesDTO() {
    }

    public DemTitulacionesDTO(Long id, Long idPostulante, Integer idBneAreas, String nombreBneAreas, Integer idBneNivelesEducativos,
            String nombreBneNivelesEducativos, Integer idBneRegiones, String nombreBneRegiones,
            Integer idBneTipoEducacionMedia, String nombreBneTipoEducacionMedia, Integer idBneTitulacionesUniv,
            String nombreBneTitulacionesUniv, Date fecFin, Integer ultCursoAprob,
            String titulacionNoExistente, Date fecAlta, Date fecModif, String observaciones,
            String titulacionMagisDoctor, String otrasTitulaciones, String especialidad, String otros,
            String nombreInst) {
        super();
        this.id = id;
        this.idPostulante = idPostulante;
        this.idBneAreas = idBneAreas;
        this.nombreBneAreas = nombreBneAreas;
        this.idBneNivelesEducativos = idBneNivelesEducativos;
        this.nombreBneNivelesEducativos = nombreBneNivelesEducativos;
        this.idBneRegiones = idBneRegiones;
        this.nombreBneRegiones = nombreBneRegiones;
        this.idBneTipoEducacionMedia = idBneTipoEducacionMedia;
        this.nombreBneTipoEducacionMedia = nombreBneTipoEducacionMedia;
        this.idBneTitulacionesUniv = idBneTitulacionesUniv;
        this.nombreBneTitulacionesUniv = nombreBneTitulacionesUniv;
        this.fecFin = fecFin;
        this.ultCursoAprob = ultCursoAprob;
        this.titulacionNoExistente = titulacionNoExistente;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.observaciones = observaciones;
        this.titulacionMagisDoctor = titulacionMagisDoctor;
        this.otrasTitulaciones = otrasTitulaciones;
        this.especialidad = especialidad;
        this.otros = otros;
        this.nombreInst = nombreInst;
    }

}
