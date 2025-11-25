package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;

public interface DemTitulacionJpaRepository extends JpaRepository<DemTitulaciones, Long> {

}
