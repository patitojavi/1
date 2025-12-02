package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemHabilidades;

@Repository
public interface DemHabilidadesJpaRepository extends JpaRepository<DemHabilidades, Long> {
    List<DemHabilidades> findByPerPersonasIdOrderByIdAsc(Long idPersona);
}
