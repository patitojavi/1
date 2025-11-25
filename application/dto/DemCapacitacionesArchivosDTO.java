package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DemCapacitacionesArchivosDTO implements Serializable {

    private static final long serialVersionUID = -2006234831203369443L;
    private Long id;
    private DemCapacitacionesDTO demCapacitaciones;
    private String archivo;
    private String nombre;

    public DemCapacitacionesArchivosDTO() {
    }

    public DemCapacitacionesArchivosDTO(Long id, DemCapacitacionesDTO demCapacitaciones, String archivo,
            String nombre) {
        super();
        this.id = id;
        this.demCapacitaciones = demCapacitaciones;
        this.archivo = archivo;
        this.nombre = nombre;
    }
    
}
