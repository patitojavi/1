package es.altia.bne.postulante.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncuestaProfilingDTO {
    private boolean encuestaPendiente;
    private String estado; 
}
