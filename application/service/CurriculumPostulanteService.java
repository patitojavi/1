package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;

/**
 * Servicio de alto nivel encargado de componer la información necesaria para
 * representar el curriculum vitae de un postulante.
 */
public interface CurriculumPostulanteService {

    /**
     * Obtiene todos los datos relevantes para el curriculum vitae del postulante
     * indicado.
     *
     * @param idPostulante identificador del postulante.
     * @return estructura con los datos del curriculum.
     */
    CurriculumVitaeDTO obtenerCurriculum(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
}