package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import jakarta.validation.Valid;

/**
 * Servicio para gestionar operaciones relacionadas con la tabla PER_PERSONAS.
 *
 * @author carol.chamblas
 */
public interface DatosCurricularesService {

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
    Object obtenerExperienciaProfesional(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

  
    /**
     * Obtiene el resumen del perfil laboral de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemPresentacionDTO obtenerResumenPerfil(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza la condicion laboral actual de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarResumenPerfil(@Valid DemPresentacionDTO resumenPerfilDTO)
            throws ResourceNotFoundException, ServiceException;

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
    int actualizarExperienciaLaboral(@Valid DemExpLaboralDTO experienciaLaboralDTO)
            throws ResourceNotFoundException, ServiceException;

    /**
     * Obtiene las referencias laborales de un postulante
     * @param idPostulante
     * @return 
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    DemReferenciasLaboralesDTO obtenerReferenciasLaborales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza las referencias laborales de un postulante
     * 
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarReferenciasLaborales(@Valid DemReferenciasLaboralesDTO referenciasLaboralesDTO)
            throws ResourceNotFoundException, ServiceException;

}
