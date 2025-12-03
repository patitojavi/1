package es.altia.bne.postulante.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.EncuestaProfiling;

public interface EncuestaProfilingRepository extends JpaRepository<EncuestaProfiling, Long> {

    Optional<EncuestaProfiling> findByIdPostulante(Long idPostulante);
}
