package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BneOcupacionesDTO implements Serializable {

    private static final long serialVersionUID = -3089289359061728079L;
    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero", groups = UpdateGroup.class)
    private Integer id;
    private String codigo;
    private String nombre;
    private String grupo1;
    private String grupo2;
    private String grupo3;
    private String grupo4;
    private Date fecIniVig;
    private Date fecFinVig;
    private Date fecAlta;
    private Date fecModif;
    private Integer orden;
    private Boolean cchc;

    public BneOcupacionesDTO() {
    }

    public BneOcupacionesDTO(final Integer id) {
        this.id = id;
    }

    public BneOcupacionesDTO(final String codigo, final String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public BneOcupacionesDTO(final Integer id, final String codigo, final String nombre, final String grupo1,
            final String grupo2, final String grupo3, final String grupo4, final Date fecIniVig, final Date fecFinVig,
            final Date fecAlta, final Date fecModif, final Integer orden, final Boolean cchc) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo1 = grupo1;
        this.grupo2 = grupo2;
        this.grupo3 = grupo3;
        this.grupo4 = grupo4;
        this.fecIniVig = fecIniVig;
        this.fecFinVig = fecFinVig;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.orden = orden;
        this.cchc = cchc;
    }

}
