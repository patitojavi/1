package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;

/**
 * Servicio para gestionar operaciones relacionadas con la tabla DEM_COND_LAB.
 *
 * @author carol.chamblas
 */
public interface DemCondLabService {

    /**
     * Obtiene la condicion laboral actual de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemCondLabDTO obtenerCondicionActual(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza la condicion laboral actual de un postulante. 
     * Si la entidad viene con id, lo actualiza, si no, se crea.
     * 
     * @param persona
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void guardarCondicionActual(DemCondLabDTO condicionActualDTO)
            throws ResourceNotFoundException, ServiceException;

}
