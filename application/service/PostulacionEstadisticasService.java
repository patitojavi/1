package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.EstadisticasPostulacionesDTO;

/**
 * Servicio encargado de obtener el resumen estadístico
 * de las postulaciones del postulante.
 *
 * Incluye:
 *  - postulaciones realizadas
 *  - postulaciones casadas (seleccionado)
 *  - postulaciones en proceso
 *  - ofertas que han visto su CV
 *
 * @author nelson.neculhueque
 */
public interface PostulacionEstadisticasService {

    /**
     * Obtiene las estadísticas generales del postulante.
     *
     * @param idPostulante ID del postulante
     * @return DTO con el resumen estadístico
     */
    EstadisticasPostulacionesDTO obtenerEstadisticas(Long idPostulante);
}
