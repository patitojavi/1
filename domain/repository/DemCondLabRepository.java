package es.altia.bne.postulante.domain.repository;

import java.util.Optional;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemCondLab;
import jakarta.persistence.NoResultException;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de las tablas
 * DEM_COND_LAB.
 *
 * @author carol.chamblas
 */
public interface DemCondLabRepository {

    /**
     * Obtiene la condicion laboral actual de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemCondLabDTO obtenerCondicionActual(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza la condicion laboral actual de un postulante. 
     * Si la entidad viene con id, lo actualiza, si no, se crea.
     * 
     * @param demCondLab
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    void save(DemCondLab demCondLab) throws DBAccessException;

    /**
     * Obtiene la condicion laboral actual por id
     * @param id
     * @return
     */
    Optional<DemCondLab> findById(Long id);
}
