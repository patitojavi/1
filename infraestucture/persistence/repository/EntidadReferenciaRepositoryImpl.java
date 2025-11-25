package es.altia.bne.postulante.infraestucture.persistence.repository;

import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.domain.repository.EntidadReferenciaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("entidadReferenciaRepositoryImpl")
public class EntidadReferenciaRepositoryImpl implements EntidadReferenciaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T getReference(Class<T> clazz, Object id) {
        return entityManager.getReference(clazz, id);
    }
}
