package es.altia.bne.postulante.application.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DemVehiculosDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1286958978734283922L;
    private Long id;
    private Long idPostulante;
    private Integer idBneVehiculo;
    private String nombreBneVehiculo;
    private Date fecAlta;
    private Date fecModif;
//    private Boolean modifica;

    public DemVehiculosDTO() {
    }

    public DemVehiculosDTO(Long id, Long idPostulante, Integer idBneVehiculo, String nombreBneVehiculo,
            Date fecAlta, Date fecModif) {
        super();
        this.id = id;
        this.idBneVehiculo = idBneVehiculo;
        this.nombreBneVehiculo = nombreBneVehiculo;
        this.idPostulante = idPostulante;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
//        this.modifica = modifica;
    }

}
