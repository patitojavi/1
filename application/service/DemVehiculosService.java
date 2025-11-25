package es.altia.bne.postulante.application.service;

import java.util.List;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemVehiculosDTO;
/**
 * Servicio para gestionar operaciones relacionadas con la tabla DEM_VEHICULOS.
 *
 * @author carol.chamblas
 */
public interface DemVehiculosService {

    /**
     * Obtiene la lista de vehiculos asociados a un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    List<DemVehiculosDTO> obtenerVehiculos(Long idPersona)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza un vehiculo de un postulante. 
     * Si la entidad viene con id, lo actualiza, si no, se crea.
     * 
     * @param persona
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void guardarVehiculo(DemVehiculosDTO dto) throws ResourceNotFoundException, ServiceException;

}
