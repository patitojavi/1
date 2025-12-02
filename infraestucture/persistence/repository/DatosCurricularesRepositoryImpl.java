package es.altia.bne.postulante.infraestucture.persistence.repository;

import org.springframework.stereotype.Repository;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.domain.repository.DatosCurricularesRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del repositorio para gestionar las operaciones de acceso a
 * datos de las tablas 
 *
 *
 * @author carol.chamblas
 */
@Repository
@Log4j2
public class DatosCurricularesRepositoryImpl extends BaseAbstractRepository implements DatosCurricularesRepository {

    /**
     * Default constructor. Empty as no initialization is needed.
     * Repository functionality is inherited from BaseAbstractRepository.
     */
    public DatosCurricularesRepositoryImpl() {
    }

    @Override
    public Object obtenerExperienciaProfesional(Long idPostulante) throws NoResultException, DBAccessException {
        
        return null;
    }

    @Override
    public DemPresentacionDTO obtenerResumenPerfil(Long idPostulante) throws NoResultException, DBAccessException {
        
        return null;
    }

    @Override
    public int actualizarResumenPerfil(@Valid DemPresentacionDTO resumenPerfilDTO) throws DBAccessException {
        
        return 0;
    }

    @Override
    public DemExpLaboralDTO obtenerExperienciaLaboral(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        
        return null;
    }

    @Override
    public int actualizarExperienciaLaboral(@Valid DemExpLaboralDTO experienciaLaboralDTO) throws DBAccessException {
        
        return 0;
    }

    @Override
    public DemReferenciasLaboralesDTO obtenerReferenciasLaborales(Long idPostulante)
            throws NoResultException, DBAccessException {
        
        return null;
    }

    @Override
    public int actualizarReferenciasLaborales(@Valid DemReferenciasLaboralesDTO referenciasLaboralesDTO)
            throws DBAccessException {
        
        return 0;
    }
}
