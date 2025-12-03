package es.altia.bne.postulante.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import es.altia.bne.postulante.application.service.InvitacionesSCTService;
import es.altia.bne.postulante.application.dto.InvitacionSCTDTO;
import es.altia.bne.postulante.domain.repository.InvitacionesSCTRepository;

@Service
@RequiredArgsConstructor
public class InvitacionesSCTServiceImpl implements InvitacionesSCTService {

    private final InvitacionesSCTRepository repository;

    @Override
    public List<InvitacionSCTDTO> obtenerInvitacionesPendientes(Long idPostulante) {

        return repository.findPendientesByPostulante(idPostulante)
                .stream()
                .map(inv -> new InvitacionSCTDTO(
                        inv.getId(),
                        inv.getNombreEmpresa(),
                        inv.getFechaInvitacion()
                ))
                .toList();
    }
}
