package es.altia.bne.postulante.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.mapper.DemTitulacionMapper;
import es.altia.bne.postulante.application.service.DemTitulacionesService;
import es.altia.bne.postulante.domain.repository.DemTitulacionesRepository;
import es.altia.bne.postulante.domain.repository.EntidadReferenciaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneAreas;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneNivelesEducativos;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneTipoEducacionMedia;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneTitulacionesUniv;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DemTitulacionesServiceImpl implements DemTitulacionesService {
    private final DemTitulacionesRepository repository;
    private final EntidadReferenciaRepository entidadReferenciaRepository;
    private final DemTitulacionMapper mapper;

    public DemTitulacionesServiceImpl(DemTitulacionesRepository repository,
            EntidadReferenciaRepository entidadReferenciaRepository, DemTitulacionMapper mapper) {
        this.repository = repository;
        this.entidadReferenciaRepository = entidadReferenciaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DemTitulacionesDTO> obtenerTitulaciones(Long idPersona)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPersona == null || idPersona == 0) {
            throw new DataValidationException("ID persona inválido");
        }
        try {
            return repository.obtenerDemTitulacion(idPersona).stream().map(mapper::toDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al obtener DemTitulaciones", ex);
            throw new ServiceException("Error al obtener DemTitulaciones", ex);
        }
    }

    @Override
    @Transactional
    public void guardarTitulacion(DemTitulacionesDTO dto) throws ResourceNotFoundException, ServiceException {
        log.info("guardarTitulacion::" + dto);
        try {
            if (dto.getId() != null) {
                DemTitulaciones entity = repository.findById(dto.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Titulacion con id " + dto.getId() + " no existe."));

                mapper.updateEntityFromDto(dto, entity);
                entity.setBneAreas(
                        entidadReferenciaRepository.getReference(BneAreas.class, dto.getIdBneAreas()));
                entity.setBneNivelesEducativos(
                        entidadReferenciaRepository.getReference(BneNivelesEducativos.class, dto.getIdBneNivelesEducativos()));
                entity.setBneTipoEducacionMedia(
                        entidadReferenciaRepository.getReference(BneTipoEducacionMedia.class, dto.getIdBneTipoEducacionMedia()));
                entity.setBneRegiones(
                        entidadReferenciaRepository.getReference(BneRegiones.class, dto.getIdBneRegiones()));
                entity.setBneTitulacionesUniv(
                        entidadReferenciaRepository.getReference(BneTitulacionesUniv.class, dto.getIdBneTitulacionesUniv()));
                repository.save(entity);

            } else {
                DemTitulaciones nuevo = mapper.toEntity(dto);
                repository.save(nuevo);
            }

        } catch (DBAccessException dbaex) {
            log.error("Error de acceso a BD al actualizar DemTitulacion", dbaex);
            throw new ServiceException("Error al acceder a la base de datos", dbaex);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Objeto no encontrado por ID: " + dto, ex);
        }
    }
}
