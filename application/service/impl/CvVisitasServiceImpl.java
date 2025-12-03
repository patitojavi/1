package es.altia.bne.postulante.application.service.impl;

import es.altia.bne.postulante.application.service.CvVisitasService;
import es.altia.bne.postulante.domain.repository.CvVisitasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Obtiene la cantidad de visitas al CV del postulante.
 */
@Service
@RequiredArgsConstructor
public class CvVisitasServiceImpl implements CvVisitasService {

    private final CvVisitasRepository cvVisitasRepository;

    @Override
    public int obtenerCantidadVisitas(Long idPostulante) {
        return cvVisitasRepository.countVisitasCv(idPostulante);
    }
}
