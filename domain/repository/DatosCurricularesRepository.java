package es.altia.bne.postulante.domain.repository;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de las tablas
 * TODO.
 *
 * @author carol.chamblas
 */
public interface DatosCurricularesRepository {

    /**
     * Obtiene los datos de experiencia profesional de un postulante. Esto incluye:
     * condicion laboral actual, resumen perfil, experiencia laboral y referencias
     * laborales.
     * 
     * @param idPostulante
     * @return datos personales de un postulante
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    Object obtenerExperienciaProfesional(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Obtiene el resumen del perfil laboral de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemPresentacionDTO obtenerResumenPerfil(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza la condicion laboral actual de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarResumenPerfil(@Valid DemPresentacionDTO resumenPerfilDTO) throws DBAccessException;

    /**
     * Obtiene la experiencia laboral de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemExpLaboralDTO obtenerExperienciaLaboral(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza la experiencia laboral de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarExperienciaLaboral(@Valid DemExpLaboralDTO experienciaLaboralDTO) throws DBAccessException;

    /**
     * Obtiene las referencias laborales de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemReferenciasLaboralesDTO obtenerReferenciasLaborales(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza las referencias laborales de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarReferenciasLaborales(@Valid DemReferenciasLaboralesDTO referenciasLaboralesDTO) throws DBAccessException;

}
