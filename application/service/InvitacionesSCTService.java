package es.altia.bne.postulante.application.service;

import java.util.List;
import es.altia.bne.postulante.application.dto.InvitacionSCTDTO;

public interface InvitacionesSCTService {
    List<InvitacionSCTDTO> obtenerInvitacionesPendientes(Long idPostulante);
}
