package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemExperienciaEducativaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long idPostulante;
    private String institucion;
    private String titulo;
    private String nivel;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean enCurso;
    private String documentoRespaldo;
}