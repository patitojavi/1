package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.ObtenerDatosPersonalesResponseDTO;
import es.altia.bne.postulante.application.mapper.PerPersonaMapper;
import es.altia.bne.postulante.application.service.ObtenerDatosPersonalesService;
import es.altia.bne.postulante.domain.repository.DatosPostulanteRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del servicio para obtener todos los datos personales completos de un postulante.
 * 
 * Historia de usuario: Obtención de Datos Personales (GET)
 * 
 * Criterios de Aceptación:
 * 1. Datos Personales Básicos: información fundamental de identificación
 * 2. Datos de Contacto: información de contacto registrada
 * 3. Direcciones: todas las direcciones asociadas al perfil
 * 4. Datos de Discapacidad: información relacionada con discapacidades si está registrada
 * 5. Datos Personales Adicionales: información complementaria del perfil
 * 6. Escenario de Error: lanzar DataAccessException con mensaje "Se necesita el id de la persona"
 * 
 * @author assistant
 */
/**
 * Implementación del servicio para obtener todos los datos personales completos de un postulante.
 * 
 * Historia de usuario: Obtención de Datos Personales (GET)
 * 
 * Criterios de Aceptación:
 * 1. Datos Personales Básicos: información fundamental de identificación
 * 2. Datos de Contacto: información de contacto registrada
 * 3. Direcciones: todas las direcciones asociadas al perfil
 * 4. Datos de Discapacidad: información relacionada con discapacidades si está registrada
 * 5. Datos Personales Adicionales: información complementaria del perfil
 * 6. Escenario de Error: lanzar DataAccessException con mensaje "Se necesita el id de la persona"
 * 
 * Utiliza PerPersonaMapper para convertir datos internos (PerPersonaDTO) a DTOs específicos.
 * 
 * @author assistant
 */
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObtenerDatosPersonalesServiceImpl implements ObtenerDatosPersonalesService {

    private final DatosPostulanteRepository datosRepository;
    private final PerPersonaMapper perPersonaMapper;

    /**
     * Obtiene todos los datos personales completos de un postulante.
     * 
     * @param idPostulante ID único del postulante
     * @return ObtenerDatosPersonalesResponseDTO con toda la información
     * @throws DataValidationException si el ID es nulo o cero, con mensaje "Se necesita el id de la persona"
     * @throws ResourceNotFoundException si el postulante no existe
     * @throws ServiceException si hay error en acceso a BD
     */
    @Override
    @Transactional(readOnly = true)
    public ObtenerDatosPersonalesResponseDTO obtenerDatosPersonalesCompletos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        log.info("Iniciando obtención de datos personales completos para idPostulante: {}", idPostulante);

        // Validación: ID de postulante no puede ser nulo
        if (idPostulante == null || idPostulante == 0) {
            log.error("ID de postulante nulo o inválido recibido: {}", idPostulante);
            throw new DataValidationException("Se necesita el id de la persona");
        }

        try {
            // Construir la respuesta con todos los datos organizados
            ObtenerDatosPersonalesResponseDTO response = ObtenerDatosPersonalesResponseDTO.builder()
                    .timestamp(System.currentTimeMillis())
                    .build();

            // 1. Obtener Datos Personales Básicos
            log.debug("Obteniendo datos básicos del postulante: {}", idPostulante);
            DatosBasicosDTO datosBasicos = datosRepository.obtenerDatosBasicos(idPostulante);
            response.setDatosBasicos(datosBasicos);

            // 2. Obtener Datos de Contacto
            log.debug("Obteniendo datos de contacto del postulante: {}", idPostulante);
            DatosContactoDTO datosContacto = datosRepository.obtenerContacto(idPostulante);
            response.setDatosContacto(datosContacto);

            // 3. Obtener Dirección
            log.debug("Obteniendo dirección del postulante: {}", idPostulante);
            DatosDireccionDTO direccion = datosRepository.obtenerDireccion(idPostulante);
            response.setDireccion(direccion);

            // 4. Obtener Datos de Discapacidad
            log.debug("Obteniendo datos de discapacidad del postulante: {}", idPostulante);
            try {
                DatosDiscapacidadDTO discapacidad = datosRepository.obtenerDiscapacidad(idPostulante);
                response.setDiscapacidad(discapacidad);
            } catch (NoResultException e) {
                log.debug("No se encontraron datos de discapacidad para el postulante: {}", idPostulante);
                response.setDiscapacidad(null);
            }

            // 5. Obtener Sitio de Referencia (cómo se enteró del sitio)
            log.debug("Obteniendo sitio de referencia del postulante: {}", idPostulante);
            try {
                Integer idSitioReferencia = datosRepository.obtenerSitioReferencia(idPostulante);
                response.setIdSitioReferencia(idSitioReferencia);
            } catch (NoResultException e) {
                log.debug("No se encontró sitio de referencia para el postulante: {}", idPostulante);
                response.setIdSitioReferencia(null);
            }

            // 6. Información adicional: Sector ER (no disponible actualmente en repositorio)
            log.debug("Sector ER no disponible actualmente");
            response.setEsER(false);

            // Marcar que todos los datos fueron cargados exitosamente
            response.setDatosCompletos(true);

            log.info("Datos personales completos obtenidos exitosamente para idPostulante: {}", idPostulante);
            return response;

        } catch (NoResultException nrex) {
            log.error("No se encontraron datos personales para el postulante: {}", idPostulante, nrex);
            throw new ResourceNotFoundException(
                    "No se encontraron datos personales para el postulante con ID: " + idPostulante, nrex);
        } catch (DBAccessException dbaex) {
            log.error("Error al acceder a la base de datos al obtener datos personales: {}", dbaex.getMessage(), dbaex);
            throw new ServiceException("Error al acceder a la información del postulante", dbaex);
        } catch (Exception ex) {
            log.error("Error inesperado al obtener datos personales completos: {}", ex.getMessage(), ex);
            throw new ServiceException("Error inesperado al procesar la solicitud", ex);
        }
    }
}
