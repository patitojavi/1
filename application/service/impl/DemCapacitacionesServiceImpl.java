package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DemCapacitacionesServiceImpl { // implements DemCapacitacionesService {
//    private final DemCapacitacionesRepository repository;
//    private final DatosCurricularesMapper mapper;
//
//    public DemCapacitacionesServiceImpl(DemCapacitacionesRepository repository, DatosCurricularesMapper mapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public List<DemCapacitacionesDTO> obtenerPorPersona(Long idPersona)
//            throws DataValidationException, ResourceNotFoundException, ServiceException {
//        if (idPersona == null || idPersona == 0) {
//            throw new DataValidationException("ID persona inválido");
//        }
//        try {
//            return repository.findByPerPersonasId(idPersona).stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//        } catch (Exception ex) {
//            log.error("Error al obtener DemCapacitaciones", ex);
//            throw new ServiceException("Error al obtener DemCapacitaciones", ex);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void guardar(DemCapacitacionesDTO dto) throws ServiceException {
//        try {
//            repository.save(mapper.toEntity(dto));
//        } catch (Exception ex) {
//            log.error("Error al guardar DemCapacitaciones", ex);
//            throw new ServiceException("Error al guardar DemCapacitaciones", ex);
//        }
//    }
}
