package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.CvProfileDTO;

public interface CvProfileService {
    CvProfileDTO getCvProfile(Long idPostulante);
}
