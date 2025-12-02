package es.altia.bne.postulante.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemExpLaboral {

    private Long id;

    private Long idPostulante;

    private Integer idOcupacion;

    private Integer idRegion;

    private String nombreRegion;

    private String razonSocial;

    private Date fecIni;

    private Date fecFin;

    private Integer numMeses;

    private BigDecimal sueldo;

    private String descripcion;

    private Long codFichero;

    private Date fecAlta;

    private Date fecModif;

    private String referencias;

    private Boolean actualidad;

    private String otrasOcupaciones;
}