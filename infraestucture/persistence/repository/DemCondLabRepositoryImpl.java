package es.altia.bne.postulante.infraestucture.persistence.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.domain.repository.DemCondLabRepository;
import es.altia.bne.postulante.infraestucture.persistence.query.PostulanteMicroserviceQueryGroups;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemCondLab;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemCondLabJpaRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("demCondLabRepositoryImpl")
public class DemCondLabRepositoryImpl extends BaseAbstractRepository implements DemCondLabRepository {

    @Autowired
    private DemCondLabJpaRepository jpaRepository;

    @Override
    public Optional<DemCondLab> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void save(DemCondLab entity) {
        jpaRepository.save(entity);
    }

    @Override
    public DemCondLabDTO obtenerCondicionActual(Long idPostulante) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.CURRICULAR, "obtenerCondicionActualJPQL");
            TypedQuery<DemCondLabDTO> query = entityManager.createQuery(jpql, DemCondLabDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerCondicionActual repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerCondicionActual repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

}
