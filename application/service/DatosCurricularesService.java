package es.altia.bne.postulante.application.service;

import java.util.List;
import jakarta.validation.Valid;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.*;

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
     * @return lista de referencias laborales
     * @throws DataValidationException
     * @throws ResourceNotFoundException
     * @throws ServiceException
     * 
     */
    List<DemReferenciasLaboralesDTO> obtenerReferenciasLaborales(Long idPostulante)
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
    
    // Métodos para datos básicos y personales
    DatosBasicosDTO obtenerDatosBasicos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    DatosContactoDTO obtenerDatosContacto(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    DatosDireccionDTO obtenerDireccion(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    // Métodos para formación y competencias
    List<DemCapacitacionesDTO> obtenerCapacitaciones(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemIdiomasDTO> obtenerIdiomas(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemCertificacionesDTO> obtenerCertificaciones(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemExperienciaEducativaDTO> obtenerExperienciasEducativas(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    // Métodos para experiencia laboral
    List<DemExpLaboralDTO> obtenerExperienciasLaborales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    // Métodos para información complementaria
    DatosDiscapacidadDTO obtenerDiscapacidad(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemHabilidadesDTO> obtenerHabilidades(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemConocimientosDTO> obtenerConocimientos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
            
    List<DemLogrosDTO> obtenerLogros(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
    
    /**
     * Obtiene todos los datos curriculares consolidados de un postulante en un único DTO.
     * Este método combina toda la información curricular, incluyendo:
     * - Datos básicos y personales
     * - Información laboral actual
     * - Resumen y experiencia
     * - Formación y competencias
     * - Información complementaria
     * 
     * @param idPostulante identificador del postulante
     * @return objeto DatosCurricularesDTO con toda la información curricular
     * @throws DataValidationException si el id no es válido
     * @throws ResourceNotFoundException si el postulante no existe
     * @throws ServiceException si ocurre un error interno
     */
    DatosCurricularesDTO obtenerDatosCurriculares(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;


}
