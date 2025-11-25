package es.altia.bne.postulante.application.service;

import java.util.List;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
/**
 * Servicio para gestionar operaciones relacionadas con la tabla DEM_TITULACIONES.
 *
 * @author carol.chamblas
 */
public interface DemTitulacionesService {

    /**
     * Obtiene la lista de Titulaciones asociados a un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    List<DemTitulacionesDTO> obtenerTitulaciones(Long idPersona)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
    /**
     * Actualiza una Titulacion de un postulante. 
     * Si la entidad viene con id, lo actualiza, si no, se crea.
     * 
     * @param persona
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void guardarTitulacion(DemTitulacionesDTO dto) throws ResourceNotFoundException, ServiceException;

}
