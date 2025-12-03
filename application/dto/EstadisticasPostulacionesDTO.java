package es.altia.bne.postulante.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstadisticasPostulacionesDTO {
    private int postuladas;
    private int casadas;
    private int enProceso;
    private int vistas;
}
