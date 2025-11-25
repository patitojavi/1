package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemVehiculos;

public interface DemVehiculoJpaRepository extends JpaRepository<DemVehiculos, Long> {

}
