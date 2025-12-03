package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.EstadoPerfilDTO;

public interface EstadoPerfilService {

    EstadoPerfilDTO obtenerEstadoPerfil(Long idPostulante);

    boolean perfilCompleto(Long idPostulante);
}
