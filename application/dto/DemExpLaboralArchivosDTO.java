package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DemExpLaboralArchivosDTO implements Serializable {
    private static final long serialVersionUID = 3288498642289012806L;
    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Long id;
    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long idDemExpLaboral;
    private String archivo;
    private String nombre;

    public DemExpLaboralArchivosDTO() {
    }

    public DemExpLaboralArchivosDTO(Long id,
            Long idDemExpLaboral,
            String archivo, String nombre) {
        super();
        this.id = id;
        this.idDemExpLaboral = idDemExpLaboral;
        this.archivo = archivo;
        this.nombre = nombre;
    }

}
