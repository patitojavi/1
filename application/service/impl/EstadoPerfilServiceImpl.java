package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import es.altia.bne.postulante.application.dto.EstadoPerfilDTO;
import es.altia.bne.postulante.application.service.EstadoPerfilService;
import es.altia.bne.postulante.domain.repository.EstadoPerfilRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoPerfilServiceImpl implements EstadoPerfilService {

    private final EstadoPerfilRepository estadoPerfilRepository;

    @Override
    public EstadoPerfilDTO obtenerEstadoPerfil(Long idPostulante) {

        return estadoPerfilRepository.findByIdPostulante(idPostulante)
                .map(e -> new EstadoPerfilDTO(
                        e.getPerfilCompleto(),
                        e.getPerfilCompleto() ? 100 : 50   // porcentaje estimado
                ))
                .orElse(new EstadoPerfilDTO(false, 0));
    }

    @Override
    public boolean perfilCompleto(Long idPostulante) {
        return estadoPerfilRepository.findByIdPostulante(idPostulante)
                .map(e -> e.getPerfilCompleto())
                .orElse(false);
    }
}
