package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.HomePostulanteDTO;
import jakarta.validation.Valid;

/**
 * Servicio para la obtención del resumen general del postulante (Home BNE 2.0).
 * 
 * Este servicio consolida información de distintas fuentes, incluyendo:
 * estadísticas de postulaciones, invitaciones SCT, datos curriculares,
 * ferias disponibles y estado general del perfil.
 * 
 * Implementa la historia de usuario: "Visualización del Home del Postulante (GET)".
 *
 * @author nelson.neculhueque
 */
public interface HomePostulanteService {

    /**
     * Obtiene la información principal del postulante para el Home.
     * 
     * La información incluye:
     * <ul>
     *   <li>Estadísticas de postulaciones (postuladas, casadas, en proceso, vistas)</li>
     *   <li>Invitaciones SCT pendientes</li>
     *   <li>Datos personales y curriculares básicos</li>
     *   <li>Estado del perfil y consentimiento CV</li>
     *   <li>Indicadores adicionales (ferias activas, fondos de cesantía, encuestas)</li>
     * </ul>
     * 
     * @param idPostulante identificador único del postulante autenticado
     * @return objeto {@link HomePostulanteDTO} con la información consolidada
     * @throws DataValidationException si el identificador es nulo o inválido
     * @throws ResourceNotFoundException si el postulante no existe o no tiene información asociada
     * @throws ServiceException si ocurre un error en la consulta o consolidación de datos
     */
    HomePostulanteDTO obtenerHome(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException;

    /**
     * Actualiza el estado general del perfil del postulante en el Home.
     * 
     * Permite registrar cambios de indicadores visibles en el Home (ej. consentimiento CV,
     * ferias activas, estado de perfil completo, etc.).
     * 
     * @param homePostulanteDTO objeto con los valores a actualizar
     * @return número de registros afectados
     * @throws ResourceNotFoundException si el postulante no existe
     * @throws ServiceException si ocurre un error en la actualización
     */
    int actualizarEstadoHome(@Valid HomePostulanteDTO homePostulanteDTO)
            throws ResourceNotFoundException, ServiceException;
}
