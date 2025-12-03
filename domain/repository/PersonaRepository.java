package es.altia.bne.postulante.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;

public interface PersonaRepository extends JpaRepository<PerPersonas, Long> {
}
