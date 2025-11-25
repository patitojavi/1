package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral;

public interface DemExpLaboralJpaRepository extends JpaRepository<DemExpLaboral, Long> {

    List<DemExpLaboral> findByPerPersonasIdOrderByFecIniDesc(Long idPersona);
}