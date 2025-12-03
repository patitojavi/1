package es.altia.bne.postulante.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstadoPerfilDTO {
    private boolean perfilCompleto;
    private int porcentajeCompletitud;
}
