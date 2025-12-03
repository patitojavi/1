package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import es.altia.bne.postulante.application.dto.EncuestaProfilingDTO;
import es.altia.bne.postulante.application.service.EncuestaProfilingService;
import es.altia.bne.postulante.domain.repository.EncuestaProfilingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncuestaProfilingServiceImpl implements EncuestaProfilingService {

    private final EncuestaProfilingRepository encuestaProfilingRepository;

    @Override
    public EncuestaProfilingDTO obtenerEstadoEncuesta(Long idPostulante) {

        return encuestaProfilingRepository.findByIdPostulante(idPostulante)
                .map(e -> new EncuestaProfilingDTO(
                        e.getEncuestaPendiente(),
                        e.getEncuestaPendiente() ? "PENDIENTE" : "COMPLETADA"
                ))
                .orElse(new EncuestaProfilingDTO(false, "NO_REGISTRADA"));
    }

    @Override
    public boolean encuestaPendiente(Long idPostulante) {
        return encuestaProfilingRepository.findByIdPostulante(idPostulante)
                .map(e -> e.getEncuestaPendiente())
                .orElse(false);
    }
}
