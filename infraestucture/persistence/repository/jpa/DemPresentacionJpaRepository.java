package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemPresentacion;

public interface DemPresentacionJpaRepository extends JpaRepository<DemPresentacion, Long> {

    Optional<DemPresentacion> findFirstByPerPersonasIdOrderByFecModifDesc(Long idPersona);
}