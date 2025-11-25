package es.altia.bne.postulante.application.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import es.altia.bne.postulante.application.dto.DireccionDTO;
import es.altia.bne.postulante.application.service.DireccionPostulanteService;
import es.altia.bne.postulante.domain.repository.EntidadReferenciaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneComunas;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.PerPersonasJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DireccionPostulanteServiceImpl implements DireccionPostulanteService {

    private final PerPersonasJpaRepository repository;
    private final EntidadReferenciaRepository entidadReferenciaRepository;

    @Override
    @Transactional
    public DireccionDTO actualizarDireccion(Long idPostulante, DireccionDTO direccionDTO) {
        PerPersonas persona = repository.findById(idPostulante)
                .orElseThrow(() -> new IllegalArgumentException("No existe persona con id " + idPostulante));

        persona.setBneRegiones(entidadReferenciaRepository.getReference(BneRegiones.class, direccionDTO.getIdRegion()));
        persona.setBneComunas(entidadReferenciaRepository.getReference(BneComunas.class, direccionDTO.getIdComuna()));
        persona.setCalle(direccionDTO.getCalle());
        persona.setNumero(direccionDTO.getNumero());
        persona.setCasa(direccionDTO.getDepartamento());
        persona.setPoblacion(direccionDTO.getVilla());
        persona.setDirExtra(direccionDTO.getDireccionExtra());
        persona.setIndMostrarDiccion(direccionDTO.getMostrarDireccion());
        persona.setFecModifPersona(new Date());

        repository.save(persona);
        return direccionDTO;
    }
}