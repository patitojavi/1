package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;

public interface PerfilBasicoService {
    DatosPersonalesDTO obtenerPerfilBasico(Long idPostulante);
}