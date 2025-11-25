package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.ObtenerDatosPersonalesResponseDTO;

/**
 * Servicio para gestionar la obtención completa de datos personales de un postulante.
 * 
 * Historia de usuario: Obtención de Datos Personales (GET)
 * Como postulante del sistema quiero poder consultar todos mis datos personales de forma completa 
 * y organizada para poder verificar que toda mi información está correctamente registrada y actualizada.
 * 
 * @author assistant
 */
public interface ObtenerDatosPersonalesService {

    /**
     * Obtiene todos los datos personales completos y organizados de un postulante.
     * 
     * Criterios de Aceptación:
     * 1. Debe incluir información fundamental de identificación (datos básicos)
     * 2. Debe indicar si pertenece al sector ER (Empleador Responsable)
     * 3. Debe validar que exista un ID de postulante válido
     * 4. Debe mostrar toda la información de contacto registrada
     * 5. Debe garantizar que los datos se mapeen correctamente desde la base de datos
     * 6. Debe mostrar todas las direcciones asociadas al perfil
     * 7. Debe utilizar el mapeo adecuado para presentar la información
     * 8. Debe mostrar la información relacionada con discapacidades si está registrada
     * 9. Debe presentar estos datos en el formato estándar definido
     * 10. Debe incluir información complementaria del perfil (sitio de referencia)
     * 
     * Escenario de Error:
     * Si no se proporciona un ID de postulante válido (valor nulo), debe lanzar una 
     * excepción DataAccessException con el mensaje "Se necesita el id de la persona"
     * y no debe mostrar ninguna información personal.
     * 
     * @param idPostulante ID único del postulante a consultar
     * @return ObtenerDatosPersonalesResponseDTO con toda la información personal organizada
     * @throws DataValidationException si el ID del postulante es nulo o inválido, 
     *                                  con mensaje "Se necesita el id de la persona"
     * @throws ResourceNotFoundException si el postulante no existe en el sistema
     * @throws ServiceException si hay un error al acceder a la base de datos
     */
    ObtenerDatosPersonalesResponseDTO obtenerDatosPersonalesCompletos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
}
