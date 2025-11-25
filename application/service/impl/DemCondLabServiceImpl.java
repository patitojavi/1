package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import es.altia.bne.common.constants.Constants;
import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.mapper.DemCondLabMapper;
import es.altia.bne.postulante.application.service.DemCondLabService;
import es.altia.bne.postulante.domain.repository.DemCondLabRepository;
import es.altia.bne.postulante.domain.repository.EntidadReferenciaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneActEconomica;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneSitLaboral;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemCondLab;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del servicio encargado del tratamiento de datos curriculares
 * relacionados con los postulantes.
 *
 * @author carol.chamblas
 */
@Service
@Log4j2
public class DemCondLabServiceImpl implements DemCondLabService {
    private final DemCondLabRepository demCondLabRepository;
    private final EntidadReferenciaRepository entidadReferenciaRepository;

    private final DemCondLabMapper mapper;

    public DemCondLabServiceImpl(DemCondLabRepository demCondLabRepository,
            EntidadReferenciaRepository entidadReferenciaRepository, DemCondLabMapper datosMapper) {
        this.mapper = datosMapper;
        this.demCondLabRepository = demCondLabRepository;
        this.entidadReferenciaRepository = entidadReferenciaRepository;
    }

    @Override
    public DemCondLabDTO obtenerCondicionActual(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        if ((idPostulante == null) || (idPostulante == 0)) {
            throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
        }

        try {
            // 🔥 CORREGIDO: ya NO se mapea, el Repository YA devuelve un DTO
            return demCondLabRepository.obtenerCondicionActual(idPostulante);

        } catch (NoResultException nrex) {
            log.error("obtenerCondicionActual::nrex::" + nrex.getMessage());
            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
        } catch (DBAccessException dbaex) {
            log.error("obtenerCondicionActual::dbaex::" + dbaex.getMessage());
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    @Transactional
    public void guardarCondicionActual(DemCondLabDTO dto) throws ResourceNotFoundException, ServiceException {

        log.info("actualizarCondicionActual::" + dto);
        try {
            if (dto.getId() != null) {
                DemCondLab entity = demCondLabRepository.findById(dto.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Condición laboral con id " + dto.getId() + " no existe."));

                mapper.updateEntityFromDto(dto, entity);
                entity.setBneSitLaboral(
                        entidadReferenciaRepository.getReference(BneSitLaboral.class, dto.getIdSitLaboral()));

                entity.setBneActEconomica(
                        entidadReferenciaRepository.getReference(BneActEconomica.class, dto.getIdActEconomica()));
                demCondLabRepository.save(entity);

            } else {
                DemCondLab nuevo = mapper.toEntity(dto);
                demCondLabRepository.save(nuevo);
            }

        } catch (DBAccessException dbaex) {
            log.error("Error de acceso a BD al actualizar condición laboral", dbaex);
            throw new ServiceException("Error al acceder a la base de datos", dbaex);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Sit. laboral no encontrada con ID: " + dto.getIdSitLaboral(), ex);
        }
    }
}
