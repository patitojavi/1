package es.altia.bne.postulante.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemVehiculosDTO;
import es.altia.bne.postulante.application.mapper.DemVehiculoMapper;
import es.altia.bne.postulante.application.service.DemVehiculosService;
import es.altia.bne.postulante.domain.repository.DemVehiculosRepository;
import es.altia.bne.postulante.domain.repository.EntidadReferenciaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneVehiculos;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemVehiculos;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DemVehiculosServiceImpl implements DemVehiculosService {
    private final DemVehiculosRepository repository;
    private final EntidadReferenciaRepository entidadReferenciaRepository;
    private final DemVehiculoMapper mapper;

    public DemVehiculosServiceImpl(DemVehiculosRepository repository, EntidadReferenciaRepository entidadReferenciaRepository, DemVehiculoMapper mapper) {
        this.repository = repository;
        this.entidadReferenciaRepository = entidadReferenciaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DemVehiculosDTO> obtenerVehiculos(Long idPersona)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPersona == null || idPersona == 0) {
            throw new DataValidationException("ID persona inválido");
        }
        try {
            return repository.obtenerDemVehiculos(idPersona).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error al obtener DemVehiculos", ex);
            throw new ServiceException("Error al obtener DemVehiculos", ex);
        }
    }

    @Override
    @Transactional
    public void guardarVehiculo(DemVehiculosDTO dto) throws ResourceNotFoundException, ServiceException {
        log.info("actualizarVehiculo::" + dto);
        try {
            if (dto.getId() != null) {
                DemVehiculos entity = repository.findById(dto.getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Vehiculo con id " + dto.getId() + " no existe."));

                mapper.updateEntityFromDto(dto, entity);
                entity.setBneVehiculos(
                        entidadReferenciaRepository.getReference(BneVehiculos.class, dto.getIdBneVehiculo()));
                repository.save(entity);

            } else {
                DemVehiculos nuevo = mapper.toEntity(dto);
                repository.save(nuevo);
            }

        } catch (DBAccessException dbaex) {
            log.error("Error de acceso a BD al actualizar vehiculo", dbaex);
            throw new ServiceException("Error al acceder a la base de datos", dbaex);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("BNEVehiculo no encontrado con ID: " + dto.getIdBneVehiculo(), ex);
        }
    }
}
