package es.altia.bne.postulante.application.service.impl;

import es.altia.bne.postulante.application.dto.EstadisticasPostulacionesDTO;
import es.altia.bne.postulante.application.service.PostulacionEstadisticasService;
import es.altia.bne.postulante.domain.repository.PostulacionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Obtiene las estadísticas de postulaciones del postulante.
 */
@Service
@RequiredArgsConstructor
public class PostulacionEstadisticasServiceImpl implements PostulacionEstadisticasService {

    private final PostulacionesRepository postulacionesRepository;

    @Override
    public EstadisticasPostulacionesDTO obtenerEstadisticas(Long idPostulante) {

        return EstadisticasPostulacionesDTO.builder()
                .postuladas(postulacionesRepository.countPostuladas(idPostulante))
                .casadas(postulacionesRepository.countCasadas(idPostulante))
                .enProceso(postulacionesRepository.countEnProceso(idPostulante))
                .vistas(postulacionesRepository.countVistas(idPostulante))
                .build();
    }
}
