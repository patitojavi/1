package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral;

@Repository
public interface DemExpLaboralJpaRepository extends JpaRepository<DemExpLaboral, Long> {
    List<DemExpLaboral> findByPerPersonasIdOrderByFecIniDesc(Long idPostulante);
    boolean existsByIdAndPerPersonasId(Long id, Long idPostulante);
}