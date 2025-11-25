package es.altia.bne.postulante.infraestucture.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.domain.repository.DemVehiculosRepository;
import es.altia.bne.postulante.infraestucture.persistence.query.PostulanteMicroserviceQueryGroups;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemVehiculos;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemVehiculoJpaRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("demVehiculoImpl")
public class DemVehiculoRepositoryImpl extends BaseAbstractRepository implements DemVehiculosRepository {

    @Autowired
    private DemVehiculoJpaRepository jpaRepository;

    @Override
    public List<DemVehiculos> obtenerDemVehiculos(Long idPostulante) throws NoResultException, DBAccessException {
        try {
            // 🔥 Query correcta de vehículos
            String jpql = loadQuery(
                    PostulanteMicroserviceQueryGroups.CURRICULAR,
                    "obtenerDemVehiculosJPQL"
            );

            TypedQuery<DemVehiculos> query = entityManager.createQuery(jpql, DemVehiculos.class);
            query.setParameter("idPostulante", idPostulante);

            return query.getResultList();

        } catch (NoResultException nrex) {
            log.error("obtenerDemVehiculos repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerDemVehiculos repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(DemVehiculos entity) throws DBAccessException {
        jpaRepository.save(entity);
    }

    @Override
    public Optional<DemVehiculos> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
