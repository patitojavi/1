package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Condicion laboral 
 * @author carol.chamblas
 */
@Data
public class DemCondLabDTO implements Serializable {
    private static final long serialVersionUID = 3056585080675814989L;

    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Long id;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long idPostulante;
    @NotNull(message = "La activiadad económoca no puede estar vacío")
    @Positive(message = "El salario debe ser mayor que cero")
    private Integer idActEconomica;
    private String nombreActEconomica;
    @NotNull(message = "La situaciób laboral no puede estar vacío")
    @Positive(message = "El salario debe ser mayor que cero")
    private Integer idSitLaboral;
    private String nombreSitLaboral;
    private Date fecIni;
    @NotNull(message = "El salario no puede estar vacío")
    @Positive(message = "El salario debe ser mayor que cero")
    private BigDecimal salario;
    private Date fecAlta;
    private Date fecModif;

    private Boolean buscandoEmpleo;

    public DemCondLabDTO() {
    }

    public DemCondLabDTO(Long id, Long idPostulante, Integer idActEconomica, String nombreActEconomica, Integer idSitLaboral,
            String nombreSitLaboral, Date fecIni, BigDecimal salario, Date fecAlta, Date fecModif,
            Boolean buscandoEmpleo) {
        super();
        this.id = id;
        this.idPostulante = idPostulante;
        this.idActEconomica = idActEconomica;
        this.nombreActEconomica = nombreActEconomica;
        this.idSitLaboral = idSitLaboral;
        this.nombreSitLaboral = nombreSitLaboral;
        this.fecIni = fecIni;
        this.salario = salario;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.buscandoEmpleo = buscandoEmpleo;
    }

}
