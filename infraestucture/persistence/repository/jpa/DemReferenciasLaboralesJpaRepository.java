package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemReferenciasLaborales;

public interface DemReferenciasLaboralesJpaRepository extends JpaRepository<DemReferenciasLaborales, Long> {

    List<DemReferenciasLaborales> findByPerPersonasIdOrderByIdAsc(Long idPersona);
}