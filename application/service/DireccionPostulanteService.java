package es.altia.bne.postulante.application.service;

import es.altia.bne.postulante.application.dto.DireccionDTO;

public interface DireccionPostulanteService {

    DireccionDTO actualizarDireccion(Long idPostulante, DireccionDTO direccionDTO);
}