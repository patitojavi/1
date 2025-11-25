package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import es.altia.bne.empresa.api.validation.UpdateGroup;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Carta de presentacion o "resumen de perfil" en front
 * @author carol.chamblas
 */
@Data
public class DemPresentacionDTO implements Serializable {
    private static final long serialVersionUID = 2160733131334134162L;

    @NotNull(message = "El id no puede estar vacío", groups = UpdateGroup.class)
    @Positive(message = "El id debe ser mayor que cero")
    private Long id;

    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long idPostulante;

    @NotNull(message = "El id no puede estar vacío")
    private String textoCarta;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaModif;

    public DemPresentacionDTO() {
    }

    public DemPresentacionDTO(Long id, Long idPostulante, String textoCarta, Date fechaAlta, Date fechaModif) {
        super();
        this.id = id;
        this.idPostulante = idPostulante;
        this.textoCarta = textoCarta;
        this.fechaAlta = fechaAlta;
        this.fechaModif = fechaModif;
    }

}
