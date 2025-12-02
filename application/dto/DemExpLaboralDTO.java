package es.altia.bne.postulante.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DemExpLaboralDTO {

    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Long id;
    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long idPostulante;
    private BneOcupacionesDTO bneOcupaciones;
    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Integer idRegion;
    private String nombreRegion;

    @NotEmpty
    @Length(max = 50)
    private String razonSocial;

    @NotEmpty
    private Date fecIni;

    @NotEmpty
    private Date fecFin;
    private Integer numMeses;
    private BigDecimal sueldo;
    private String descripcion;
    private Long codFichero;
    private Date fecAlta;
    private Date fecModif;
    private String referencias;
    private DemExpLaboralArchivosDTO demExpLaboralArchivos;

    private Boolean modifica;
    private Boolean actualidad;

    private String otrasOcupaciones;

    public DemExpLaboralDTO() {
    }

    public DemExpLaboralDTO(Long id) {
        this.id = id;
    }
}
