package es.altia.bne.postulante.domain.repository;

import java.util.List;
import java.util.Optional;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;
import jakarta.persistence.NoResultException;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la tabla
 * DEM_COND_LAB.
 *
 * @author carol.chamblas
 */
public interface DemTitulacionesRepository {

    /**
     * Obtiene las titulaciones de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    List<DemTitulaciones> obtenerDemTitulacion(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza la titulacion de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void save(DemTitulaciones entity) throws DBAccessException;

    /**
     * Obtiene una titulacion por id
     * @param id
     * @return
     */
    Optional<DemTitulaciones> findById(Long id);
}
