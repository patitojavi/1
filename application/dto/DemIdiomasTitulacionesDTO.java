package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DemIdiomasTitulacionesDTO implements Serializable {
    private static final long serialVersionUID = 6971289203261127823L;
    private Integer id;
    private DemIdiomasDTO idioma;
    private Integer idBneIdiomasTitulaciones;
    private String nombreneBneIdiomasTitulaciones;

    public DemIdiomasTitulacionesDTO() {
    }

    public DemIdiomasTitulacionesDTO(Integer id, DemIdiomasDTO idioma, Integer idBneIdiomasTitulaciones,
            String nombreneBneIdiomasTitulaciones) {
        super();
        this.id = id;
        this.idioma = idioma;
        this.idBneIdiomasTitulaciones = idBneIdiomasTitulaciones;
        this.nombreneBneIdiomasTitulaciones = nombreneBneIdiomasTitulaciones;
    }

}
