package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DemReferenciasLaboralesServiceImpl { // implements DemReferenciasLaboralesService {
//    private final DemReferenciasLaboralesRepository repository;
//    private final DatosCurricularesMapper mapper;
//
//    public DemReferenciasLaboralesServiceImpl(DemReferenciasLaboralesRepository repository, DatosCurricularesMapper mapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public List<DemReferenciasLaboralesDTO> obtenerPorPersona(Long idPersona)
//            throws DataValidationException, ResourceNotFoundException, ServiceException {
//        if (idPersona == null || idPersona == 0) {
//            throw new DataValidationException("ID persona inválido");
//        }
//        try {
//            return repository.findByPerPersonasId(idPersona).stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//        } catch (Exception ex) {
//            log.error("Error al obtener DemReferenciasLaborales", ex);
//            throw new ServiceException("Error al obtener DemReferenciasLaborales", ex);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void guardar(DemReferenciasLaboralesDTO dto) throws ServiceException {
//        try {
//            repository.save(mapper.toEntity(dto));
//        } catch (Exception ex) {
//            log.error("Error al guardar DemReferenciasLaborales", ex);
//            throw new ServiceException("Error al guardar DemReferenciasLaborales", ex);
//        }
//    }
}
