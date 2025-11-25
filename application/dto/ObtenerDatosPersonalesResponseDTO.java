package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la respuesta de obtención de datos personales completos del postulante.
 * Agrupa toda la información personal organizada en secciones.
 * 
 * Historia de usuario: Obtención de Datos Personales (GET)
 * 
 * @author assistant
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con datos personales completos del postulante organizados en secciones")
public class ObtenerDatosPersonalesResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Datos Personales Básicos: información fundamental de identificación", 
            example = "Incluye nombre, documento, estado civil, etc.")
    private DatosBasicosDTO datosBasicos;

    @Schema(description = "Datos de Contacto: información de contacto registrada", 
            example = "Email, teléfono, etc.")
    private DatosContactoDTO datosContacto;

    @Schema(description = "Direcciones: todas las direcciones asociadas al perfil", 
            example = "Dirección registrada con calle, número, región, etc.")
    private DatosDireccionDTO direccion;

    @Schema(description = "Datos de Discapacidad: información relacionada con discapacidades si está registrada", 
            example = "Tipo de discapacidad si la hay")
    private DatosDiscapacidadDTO discapacidad;

    @Schema(description = "ID del sitio de referencia: cómo se enteró del sitio", 
            example = "1")
    private Integer idSitioReferencia;

    @Schema(description = "Indica si el postulante pertenece al sector ER (Empleador Responsable)", 
            example = "false")
    private Boolean esER;

    @Schema(description = "Indicador de que los datos fueron cargados exitosamente", 
            example = "true")
    private Boolean datosCompletos;

    @Schema(description = "Timestamp de la consulta en milisegundos", 
            example = "1699014800000")
    private Long timestamp;
}
