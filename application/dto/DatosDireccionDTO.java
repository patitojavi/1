package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos de Dirección: información de direcciones del postulante")
public class DatosDireccionDTO implements Serializable  {
    private static final long serialVersionUID = -9038140009445022919L;

/*
 * Anotación    ¿Qué valida?    Ejemplo
@NotNull    No debe ser null    @NotNull Long id;
@NotEmpty   No debe ser null ni estar vacío (colecciones, strings)  @NotEmpty List<String> items;
@NotBlank   No debe ser null, ni vacío, ni solo espacios    @NotBlank String nombre;
@Size(min, max) Tamaño de strings, listas, arrays   @Size(min=3, max=50) String nombre;
@Min(n) Mínimo valor numérico   @Min(18) int edad;
@Max(n) Máximo valor numérico   @Max(100) int porcentaje;
@Email  Formato de correo electrónico válido    @Email String correo;
@Pattern(regexp=...)    Validación por expresión regular    @Pattern(regexp="\\d{9}") String rut;
@Positive   Número positivo (>0)    @Positive BigDecimal sueldo;
@PositiveOrZero >= 0    @PositiveOrZero int stock;
@Negative   < 0 
@Past   Fecha en el pasado  @Past LocalDate fechaNacimiento;
@PastOrPresent  Fecha hoy o pasada  
@Future Fecha futura    @Future LocalDate fechaEvento;
@FutureOrPresent    Fecha hoy o futura  
@AssertTrue Booleano debe ser true  
@AssertFalse    Booleano debe ser false

 */
    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    @Schema(description = "ID del postulante", example = "12345")
    private Long idPostulante;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    @Schema(description = "ID de la región", example = "13")
    private Integer idRegion;
    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String nombreRegion;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    @Schema(description = "ID de la comuna", example = "13101")
    private Integer idComuna;
    @Schema(description = "Nombre de la comuna", example = "Santiago")
    private String nombreComuna;

    private String dirExtra;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 100, message = "La calle no puede superar los 100 caracteres")
    @Schema(description = "Nombre de la calle", example = "Av. Libertadores")
    private String calle;
    @Size(max = 10, message = "El número no puede superar los 10 caracteres")
    @Schema(description = "Número de la dirección", example = "742")
    private String numero;
    @Size(max = 50, message = "El departamento no puede superar los 50 caracteres")
    @Schema(description = "Departamento o casa", example = "Dpto 5")
    private String casa;
    @Size(max = 100, message = "La villa no puede superar los 100 caracteres")
    @Schema(description = "Población o villa", example = "Villa Futura")
    private String poblacion;
    @Schema(description = "Indicador para mostrar dirección", example = "true")
    private Boolean indMostrarDiccion;

//    private BneComunas bneComunas;
//    private BneRegiones bneRegiones;

    public DatosDireccionDTO() {
        super();
    }

    public DatosDireccionDTO(
            Long id,
            Integer idRegion, String nombreRegion, Integer idComuna, String nombreComuna,  String calle, String numero, String casa,
            String poblacion, Boolean indMostrarDiccion) {
        super();
        this.idPostulante = id;
        this.idRegion = idRegion;
        this.nombreRegion = nombreRegion;
        this.idComuna = idComuna;
        this.nombreComuna = nombreComuna;
        this.numero = numero;
        this.casa = casa;
        this.poblacion = poblacion;
        this.indMostrarDiccion = indMostrarDiccion;
    }

}
