package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DemServiciosDTO implements Serializable {

    private static final long serialVersionUID = -3075611309910438886L;
    private Long id;
    private Integer idBneEstadosServicios;
    private String nombreBneEstadosServicios;
    private Integer idBneServicios;
    private String nombreBneServicios;
//    private CenCentrosDto cenCentros;
//    private CenUsuariosCentrosDto cenUsuariosCentros;

    private PerPersonaDTO perPersona;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecIni;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecFin;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecSolic;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecOfertado;

    @Min(value = 0)
    private Long duracion;

    @Length(max = 500)
    private String observaciones;
    private Date fecAlta;
    private Date fecModif;

    public DemServiciosDTO() {
    }

    public DemServiciosDTO(Long id, Integer idBneEstadosServicios, String nombreBneEstadosServicios,
            Integer idBneServicios, String nombreBneServicios, PerPersonaDTO perPersona, Date fecIni, Date fecFin,
            Date fecSolic, Date fecOfertado, @Min(0) Long duracion, @Length(max = 500) String observaciones,
            Date fecAlta, Date fecModif) {
        super();
        this.id = id;
        this.idBneEstadosServicios = idBneEstadosServicios;
        this.nombreBneEstadosServicios = nombreBneEstadosServicios;
        this.idBneServicios = idBneServicios;
        this.nombreBneServicios = nombreBneServicios;
        this.perPersona = perPersona;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
        this.fecSolic = fecSolic;
        this.fecOfertado = fecOfertado;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
    }

}
