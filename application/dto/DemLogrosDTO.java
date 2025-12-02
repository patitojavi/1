package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DemLogrosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long idPostulante;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String evidencia;
}