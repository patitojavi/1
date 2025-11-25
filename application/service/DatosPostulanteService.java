package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import jakarta.validation.Valid;

/**
 * Servicio para gestionar operaciones relacionadas con la tabla PER_PERSONAS.
 *
 * Permite obtener y actualizar información personal, de contacto, dirección,
 * discapacidad y sitio de referencia del postulante.
 *
 * @author carol
 */
public interface DatosPostulanteService {

    /**
     * Obtiene los datos básicos del postulante.
     */
    DatosBasicosDTO obtenerDatosBasicos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Obtiene los datos de contacto del postulante.
     */
    DatosContactoDTO obtenerContacto(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza los datos de contacto del postulante.
     */
    int actualizarContacto(@Valid DatosContactoDTO contactoDTO)
            throws ResourceNotFoundException, ServiceException;

    /**
     * Obtiene la dirección registrada del postulante.
     */
    DatosDireccionDTO obtenerDireccion(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza la dirección del postulante.
     */
    int actualizarDireccion(DatosDireccionDTO direccionDTO)
            throws ResourceNotFoundException, ServiceException;

    /**
     * Obtiene los datos personales completos del postulante,
     * incluyendo básicos, contacto, dirección, discapacidad y sitio de referencia.
     */
    DatosPersonalesDTO obtenerDatosPersonales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza los datos de discapacidad del postulante.
     */
    int actualizarDiscapacidad(@Valid DatosDiscapacidadDTO discapacidadDTO)
            throws ResourceNotFoundException, ServiceException;

    /**
     * Actualiza el sitio de referencia del postulante (cómo se enteró del portal).
     */
    int actualizarSitioReferente(@Valid PerPersonaDTO persona)
            throws ResourceNotFoundException, ServiceException;

    /**
     * Obtiene la entidad completa del postulante, con todos los datos asociados
     * (similar a una vista detallada de PER_PERSONAS).
     *
     * @param idPostulante identificador del postulante
     * @return DTO con toda la información mapeada desde la entidad {@code PerPersonas}
     * @throws DataValidationException si el ID es nulo o inválido
     * @throws ResourceNotFoundException si no se encuentra el postulante
     * @throws ServiceException si ocurre un error en la capa de datos
     */
    PerPersonaDTO obtenerPersonaCompleta(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

}
