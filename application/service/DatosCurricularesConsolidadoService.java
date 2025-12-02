package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.DatosCurricularesDTO;
import java.util.Optional;

/**
 * Servicio para obtener datos curriculares consolidados del postulante.
 * Orquesta múltiples repositorios para compilar la información completa.
 */
public interface DatosCurricularesConsolidadoService {

    /**
     * Obtiene todos los datos curriculares consolidados de un postulante
     * @param idPostulante ID del postulante
     * @return DTO con todos los datos curriculares
     */
    Optional<DatosCurricularesDTO> obtenerDatosCurriculares(Long idPostulante);

    /**
     * Actualiza los datos curriculares de un postulante
     * @param datosCurriculares DTO con los datos a actualizar
     * @return DTO actualizado
     */
    DatosCurricularesDTO actualizarDatosCurriculares(DatosCurricularesDTO datosCurriculares);

}
