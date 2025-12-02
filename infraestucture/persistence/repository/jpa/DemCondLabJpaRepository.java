package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemCondLab;

public interface DemCondLabJpaRepository extends JpaRepository<DemCondLab, Long> {
    Optional<DemCondLab> findFirstByPerPersonasIdOrderByFecModifDesc(Long idPersona);
}
