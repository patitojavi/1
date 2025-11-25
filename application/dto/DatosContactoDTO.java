package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import es.altia.bne.common.validation.annotations.Email;
import es.altia.bne.common.validation.annotations.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos de Contacto: información de contacto registrada del postulante")
public class DatosContactoDTO implements Serializable {

    private static final long serialVersionUID = 4136128558251074325L;
    @NotNull(message = "El ID del postulante no puede ser nulo")
    @Schema(description = "ID del postulante", example = "12345")
    private Long idPostulante;
    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(max = 15, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoNotificaciones.size}")
    @Phone(message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoNotificaciones.telefono}")
    @Schema(description = "Teléfono de notificación", example = "912345678")
    private String telefonoNotificaciones;

    @NotNull(message = "El teléfono alternativo no puede ser nulo")
    @Size(max = 15, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.size}")
    @Phone(message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.telefono}")
    @Schema(description = "Teléfono alternativo", example = "923456789")
    private String telefonoAlternativo;

    @Schema(description = "Recibir notificaciones por SMS", example = "true")
    private Boolean notificacionesSMS;

    @NotEmpty(message = "{postulantes.miPerfil.datosPersonales.datosContacto.email.notempty}")
    @Email(message = "{postulantes.miPerfil.datosPersonales.datosContacto.email.email}")
    @Schema(description = "Email de contacto", example = "juan@example.com")
    private String email;

    public DatosContactoDTO() {
    }

    public DatosContactoDTO(Long id, String telefonoNotificaciones, String telefonoAlternativo,
            Boolean notificacionesSMS, String email) {
        super();
        this.idPostulante = id;
        this.telefonoNotificaciones = telefonoNotificaciones;
        this.telefonoAlternativo = telefonoAlternativo;
        this.notificacionesSMS = notificacionesSMS;
        this.email = email;
    }

}
