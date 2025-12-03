package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.service.PerfilBasicoService;
import es.altia.bne.postulante.domain.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilBasicoServiceImpl implements PerfilBasicoService {

    private final PersonaRepository personaRepository;

    @Override
    public DatosPersonalesDTO obtenerPerfilBasico(Long idPostulante) {
        return personaRepository.findById(idPostulante)
                .map(persona -> new DatosPersonalesDTO(
                        null, // datos basicos
                        null, // datos contacto
                        null, // direccion
                        null, // discapacidad
                        null  // sitio
                ))
                .orElse(null);
    }
}
