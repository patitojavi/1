package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.EncuestaProfilingDTO;

public interface EncuestaProfilingService {

    EncuestaProfilingDTO obtenerEstadoEncuesta(Long idPostulante);

    boolean encuestaPendiente(Long idPostulante);
}
