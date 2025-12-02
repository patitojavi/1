package es.altia.bne.postulante.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DemConocimientosDTO {

    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Long id;

    @NotNull(message = "El id del postulante no puede estar vacío")
    @Positive(message = "El id del postulante debe ser mayor que cero")
    private Long idPostulante;

    @NotEmpty(message = "El nombre del conocimiento no puede estar vacío")
    @Size(max = 100, message = "El nombre del conocimiento no puede superar los 100 caracteres")
    private String nombre;

    @NotEmpty(message = "El tipo de conocimiento no puede estar vacío")
    @Size(max = 50, message = "El tipo de conocimiento no puede superar los 50 caracteres")
    private String tipo;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    private Integer nivelDominio;
    private String certificacion;
    private Boolean actualidad;
}