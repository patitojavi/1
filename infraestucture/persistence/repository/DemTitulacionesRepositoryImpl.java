package es.altia.bne.postulante.infraestucture.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.domain.repository.DemTitulacionesRepository;
import es.altia.bne.postulante.infraestucture.persistence.query.PostulanteMicroserviceQueryGroups;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemTitulacionJpaRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("demTitulacionesImpl")
public class DemTitulacionesRepositoryImpl extends BaseAbstractRepository implements DemTitulacionesRepository {

    @Autowired
    private DemTitulacionJpaRepository jpaRepository;

    @Override
    public List<DemTitulaciones> obtenerDemTitulacion(Long idPostulante)
            throws NoResultException, DBAccessException {

        try {
            // 1. Cargar archivo JPQL externo
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.CURRICULAR,
                                    "obtenerDemTitulacionJPQL");

            // 2. Si no existe, evitar NullPointer y lanzar excepción clara
            if (jpql == null || jpql.isBlank()) {
                log.error("No se encontró el archivo JPQL: obtenerDemTitulacionJPQL.sql");
                throw new DBAccessException("Archivo JPQL obtenerDemTitulacionJPQL no encontrado.");
            }

            // 3. Ejecutar query correctamente
            TypedQuery<DemTitulaciones> query =
                    entityManager.createQuery(jpql, DemTitulaciones.class);

            query.setParameter("idPostulante", idPostulante);

            return query.getResultList();

        } catch (NoResultException nrex) {
            log.error("obtenerDemTitulacion repo::nrex::" + nrex.getMessage());
            throw nrex;

        } catch (Exception ex) {
            log.error("obtenerDemTitulacion repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(DemTitulaciones entity) throws DBAccessException {
        jpaRepository.save(entity);
    }

    @Override
    public Optional<DemTitulaciones> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
