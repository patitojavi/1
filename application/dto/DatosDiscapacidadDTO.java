package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import es.altia.bne.common.utils.BooleanUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@Schema(description = "Datos de Discapacidad: información relacionada con discapacidades del postulante")
public class DatosDiscapacidadDTO implements Serializable {

    private static final long serialVersionUID = 3975655529375140294L;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    @Schema(description = "ID del postulante", example = "12345")
    private Long idPostulante;

    @Schema(description = "Grado de discapacidad", example = "true")
    private Boolean gradoDiscapacidad;

    @Schema(description = "Requiere ajuste en puesto de trabajo", example = "false")
    private Boolean ajustePuestoTrabajo;

    @NotNull(message = "{postulantes.miPerfil.datosPersonales.discapacidad.mostrarDiscapacidad}")
    @Schema(description = "Mostrar información de discapacidad", example = "true")
    private Boolean mostrarDiscapacidad;

    public DatosDiscapacidadDTO() {
        super();
    }

    public DatosDiscapacidadDTO(Long idPostulante, String gradoDiscapacidad, String ajustePuestoTrabajo,
            Boolean mostrarDiscapacidad) {
        super();
        log.info("DatosDiscapacidadDTO llega:" + gradoDiscapacidad + "::" + ajustePuestoTrabajo + "::");
        this.idPostulante = idPostulante;
        this.gradoDiscapacidad = BooleanUtils.parse(gradoDiscapacidad);
        this.ajustePuestoTrabajo = BooleanUtils.parse(ajustePuestoTrabajo);
        this.mostrarDiscapacidad = mostrarDiscapacidad;
        log.info("DatosDiscapacidadDTO creado:" + this);
    }

}
