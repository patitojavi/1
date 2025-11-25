package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DemIdiomasArchivosDTO implements Serializable {

    private static final long serialVersionUID = 5435799172364836126L;
    private Long id;
    private DemIdiomasDTO demIdiomas;
    private String archivo;
    private String nombre;

    public DemIdiomasArchivosDTO() {
    }

    public DemIdiomasArchivosDTO(Long id, DemIdiomasDTO demIdiomas, String archivo, String nombre) {
        super();
        this.id = id;
        this.demIdiomas = demIdiomas;
        this.archivo = archivo;
        this.nombre = nombre;
    }

}
