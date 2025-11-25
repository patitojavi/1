package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerPersonasJpaRepository extends JpaRepository<PerPersonas, Long> { }
