package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import es.altia.bne.common.validation.annotations.Email;
import es.altia.bne.common.validation.annotations.Phone;
import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DemReferenciasLaboralesDTO implements Serializable {
    private static final long serialVersionUID = -5771541754194203216L;

    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Long id;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long idPostulante;
    @Size(max = 100, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.size}")
    private String nombre;
    @Size(max = 30, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.size}")
    private String puesto;
    @Size(max = 50, message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoAlternativo.size}")
    private String empresa;

    @Phone(message = "{postulantes.miPerfil.datosPersonales.datosContacto.telefonoNotificaciones.telefono}")
    @Size(max = 12, groups = UpdateGroup.class)
    private String telefono;

    @Size(max = 50, groups = UpdateGroup.class)
    @Email(groups = UpdateGroup.class)
    private String email;

    public DemReferenciasLaboralesDTO() {
    }

    public DemReferenciasLaboralesDTO(
            @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class) @Positive(message = "El id debe ser mayor que cero") Long id,
            @NotNull(message = "El id no puede estar vacío") @Positive(message = "El id debe ser mayor que cero") Long idPostulante,
            String nombre, String puesto, String empresa, @Size(max = 9, groups = UpdateGroup.class) String telefono,
            @Size(max = 100, groups = UpdateGroup.class) String email) {
        super();
        this.id = id;
        this.idPostulante = idPostulante;
        this.nombre = nombre;
        this.puesto = puesto;
        this.empresa = empresa;
        this.telefono = telefono;
        this.email = email;
    }

}
