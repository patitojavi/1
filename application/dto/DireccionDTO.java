package es.altia.bne.postulante.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DireccionDTO {

    @NotNull(message = "La región es obligatoria")
    @Positive(message = "La región debe ser un identificador válido")
    private Integer idRegion;

    @NotNull(message = "La comuna es obligatoria")
    @Positive(message = "La comuna debe ser un identificador válido")
    private Integer idComuna;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 100, message = "La calle no puede superar los 100 caracteres")
    private String calle;

    @NotBlank(message = "El número es obligatorio")
    @Size(max = 10, message = "El número no puede superar los 10 caracteres")
    private String numero;

    @Size(max = 50, message = "El departamento no puede superar los 50 caracteres")
    private String departamento;

    @Size(max = 100, message = "La villa no puede superar los 100 caracteres")
    private String villa;

    @Size(max = 100, message = "La referencia no puede superar los 100 caracteres")
    private String direccionExtra;

    private Boolean mostrarDireccion;
}