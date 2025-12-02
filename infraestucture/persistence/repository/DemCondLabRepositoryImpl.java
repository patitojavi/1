package es.altia.bne.postulante.infraestucture.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class DemCondLabRepositoryImpl extends BaseAbstractRepository implements DemCondLabRepository {

    private final DemCondLabJpaRepository jpaRepository;

    /**
     * Constructor para la inyección de dependencias
     * 
     * @param jpaRepository repositorio JPA para operaciones con condiciones laborales
     */
    public DemCondLabRepositoryImpl(DemCondLabJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Busca una condición laboral por su ID
     * 
     * @param id identificador de la condición laboral
     * @return Optional con la condición laboral si existe
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemCondLab> findById(Long id) {
        log.debug("Buscando condición laboral con ID: {}", id);
        return jpaRepository.findById(id);
    }

    /**
     * Guarda o actualiza una condición laboral
     * 
     * @param entity entidad a guardar
     * @throws DBAccessException si ocurre un error al acceder a la base de datos
     * @throws IllegalArgumentException si la entidad es nula
     */
    @Override
    @Transactional
    public void save(DemCondLab entity) throws DBAccessException {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }
        try {
            jpaRepository.save(entity);
            log.debug("Condición laboral guardada correctamente: {}", entity.getId());
        } catch (Exception ex) {
            log.error("Error al guardar condición laboral: {}", entity, ex);
            throw new DBAccessException("Error al guardar la condición laboral", ex);
        }
    }

    /**
     * Obtiene la condición laboral actual de un postulante
     * 
     * @param idPostulante identificador del postulante
     * @return condición laboral actual del postulante
     * @throws NoResultException si no se encuentra la condición laboral
     * @throws DBAccessException si ocurre un error al acceder a la base de datos
     * @throws IllegalArgumentException si el idPostulante es nulo
     */
    @Override
    @Transactional(readOnly = true)
    public DemCondLabDTO obtenerCondicionActual(Long idPostulante) throws NoResultException, DBAccessException {
        if (idPostulante == null) {
            throw new IllegalArgumentException("El ID del postulante no puede ser nulo");
        }

        try {
            log.debug("Buscando condición laboral actual para postulante ID: {}", idPostulante);
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.CURRICULAR, "obtenerCondicionActualJPQL");
            TypedQuery<DemCondLabDTO> query = entityManager.createQuery(jpql, DemCondLabDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.warn("No se encontró condición laboral actual para el postulante ID: {}", idPostulante);
            throw nrex;
        } catch (Exception ex) {
            log.error("Error al obtener condición laboral actual para postulante ID: {}", idPostulante, ex);
            throw new DBAccessException("Error al obtener la condición laboral actual", ex);
        }
    }

    /**
     * Busca la condición laboral de un postulante por su ID
     * 
     * @param idPostulante identificador del postulante
     * @return Optional con la condición laboral del postulante si existe
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemCondLab> findByIdPostulante(Long idPostulante) {
        if (idPostulante == null) {
            return Optional.empty();
        }
        
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.CURRICULAR, "obtenerCondicionActualJPQL");
            TypedQuery<DemCondLab> query = entityManager.createQuery(jpql, DemCondLab.class);
            query.setParameter("idPostulante", idPostulante);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException nrex) {
            log.debug("No se encontró condición laboral para el postulante: {}", idPostulante);
            return Optional.empty();
        } catch (Exception ex) {
            log.error("Error al buscar condición laboral por idPostulante: {}", idPostulante, ex);
            return Optional.empty();
        }
    }

}
