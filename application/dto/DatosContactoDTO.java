package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import es.altia.bne.common.validation.annotations.Email;
import es.altia.bne.common.validation.annotations.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO que representa los datos de contacto del postulante.
 * Incluye teléfonos, email y preferencias de notificación.
 */
@Data
@Schema(description = "Datos de contacto del postulante (teléfonos, email y preferencias de notificación).")
public class DatosContactoDTO implements Serializable {

    private static final long serialVersionUID = 4136128558251074325L;

    @Schema(description = "Identificador único del postulante", example = "12345")
    @NotNull(message = "El ID del postulante no puede ser nulo")
    private Long idPostulante;

    @Schema(description = "Teléfono principal para notificaciones", example = "+56 9 7777 8888")
    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(max = 15, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoNotificaciones.size}")
    @Phone(message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoNotificaciones.telefono}")
    private String telefonoNotificaciones;

    @Schema(description = "Teléfono alternativo de contacto", example = "+56 45 234 5678")
    @NotNull(message = "El teléfono alternativo no puede ser nulo")
    @Size(max = 15, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.size}")
    @Phone(message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.telefono}")
    private String telefonoAlternativo;

    @Schema(description = "Indica si el postulante acepta recibir notificaciones SMS", example = "true")
    private Boolean notificacionesSMS;

    @Schema(description = "Correo electrónico del postulante", example = "nelson.neculhueque@bne.cl")
    @NotEmpty(message = "{postulantes.miPerfil.datosPersonales.datosContacto.email.notempty}")
    @Email(message = "{postulantes.miPerfil.datosPersonales.datosContacto.email.email}")
    private String email;

    // === Constructores ===
    public DatosContactoDTO() {
    }

    public DatosContactoDTO(Long id, String telefonoNotificaciones, String telefonoAlternativo,
                            Boolean notificacionesSMS, String email) {
        this.idPostulante = id;
        this.telefonoNotificaciones = telefonoNotificaciones;
        this.telefonoAlternativo = telefonoAlternativo;
        this.notificacionesSMS = notificacionesSMS;
        this.email = email;
    }
}
