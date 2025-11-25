package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DemExpLaboralServiceImpl { // implements DemExpLaboralService {
//    private final DemExpLaboralRepository repository;
//    private final DatosCurricularesMapper mapper;
//
//    public DemExpLaboralServiceImpl(DemExpLaboralRepository repository, DatosCurricularesMapper mapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public List<DemExpLaboralDTO> obtenerPorPersona(Long idPersona)
//            throws DataValidationException, ResourceNotFoundException, ServiceException {
//        if (idPersona == null || idPersona == 0) {
//            throw new DataValidationException("ID persona inválido");
//        }
//        try {
//            return repository.findByPerPersonasId(idPersona).stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//        } catch (Exception ex) {
//            log.error("Error al obtener DemExpLaboral", ex);
//            throw new ServiceException("Error al obtener DemExpLaboral", ex);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void guardar(DemExpLaboralDTO dto) throws ServiceException {
//        try {
//            repository.save(mapper.toEntity(dto));
//        } catch (Exception ex) {
//            log.error("Error al guardar DemExpLaboral", ex);
//            throw new ServiceException("Error al guardar DemExpLaboral", ex);
//        }
//    }
}
