package es.altia.bne.postulante.domain.repository;

import java.util.List;
import java.util.Optional;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemVehiculos;
import jakarta.persistence.NoResultException;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de las tablas
 * DEM_VEHICULOS.
 *
 * @author carol.chamblas
 */
public interface DemVehiculosRepository {

    /**
     * Obtiene los vehiculos de un postulante
     * 
     * @param idPostulante
     * @return
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    List<DemVehiculos> obtenerDemVehiculos(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza un vehiculo de un postulante. 
     * Si la entidad viene con id, lo actualiza, si no, se crea.
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void save(DemVehiculos entity) throws DBAccessException;

    /**
     * Obtiene un vehiculo por id
     * 
     * @param id
     * @return
     */
    Optional<DemVehiculos> findById(Long id);
}
