package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class DemInfLaboralDTO implements Serializable {
    private static final long serialVersionUID = 8139574152063629835L;
    private Long id;
    private Integer idBneNivelProfesional;
    private String nombreBneNivelProfesional;
    private Integer idBneOcupaciones;
    private String nombreBneOcupaciones;
    private Integer idBneRelacionesContractuales;
    private String nombreBneRelacionesContractuales;
    private Integer idBneTiposJornada;
    private String nombreBneTiposJornada;
    private PerPersonaDTO perPersona;
    private String descripcion;
    private Date fecSolic;
    private Date fecAlta;
    private Date fecModif;
    private BigDecimal salarioBruto;
    private Integer experiencia;
    private Integer idBneAmbBusqueda;
    private String nombreBneAmbBusqueda;
    private Integer idBneComuna;
    private String nombreBneComuna;
    private Integer idBneRegion;
    private String nombreBneRegion;
    private Integer idBnePais;
    private String nombreBnePais;

    private Boolean modifica;

    public DemInfLaboralDTO() {
    }

    public DemInfLaboralDTO(Long id, Integer idBneNivelProfesional, String nombreBneNivelProfesional,
            Integer idBneOcupaciones, String nombreBneOcupaciones, Integer idBneRelacionesContractuales,
            String nombreBneRelacionesContractuales, Integer idBneTiposJornada, String nombreBneTiposJornada,
            PerPersonaDTO perPersona, String descripcion, Date fecSolic, Date fecAlta, Date fecModif,
            BigDecimal salarioBruto, Integer experiencia, Integer idBneAmbBusqueda, String nombreBneAmbBusqueda,
            Integer idBneComuna, String nombreBneComuna, Integer idBneRegion, String nombreBneRegion, Integer idBnePais,
            String nombreBnePais, Boolean modifica) {
        super();
        this.id = id;
        this.idBneNivelProfesional = idBneNivelProfesional;
        this.nombreBneNivelProfesional = nombreBneNivelProfesional;
        this.idBneOcupaciones = idBneOcupaciones;
        this.nombreBneOcupaciones = nombreBneOcupaciones;
        this.idBneRelacionesContractuales = idBneRelacionesContractuales;
        this.nombreBneRelacionesContractuales = nombreBneRelacionesContractuales;
        this.idBneTiposJornada = idBneTiposJornada;
        this.nombreBneTiposJornada = nombreBneTiposJornada;
        this.perPersona = perPersona;
        this.descripcion = descripcion;
        this.fecSolic = fecSolic;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.salarioBruto = salarioBruto;
        this.experiencia = experiencia;
        this.idBneAmbBusqueda = idBneAmbBusqueda;
        this.nombreBneAmbBusqueda = nombreBneAmbBusqueda;
        this.idBneComuna = idBneComuna;
        this.nombreBneComuna = nombreBneComuna;
        this.idBneRegion = idBneRegion;
        this.nombreBneRegion = nombreBneRegion;
        this.idBnePais = idBnePais;
        this.nombreBnePais = nombreBnePais;
        this.modifica = modifica;
    }
}
