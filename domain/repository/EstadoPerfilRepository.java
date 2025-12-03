package es.altia.bne.postulante.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.EstadoPerfil;

public interface EstadoPerfilRepository extends JpaRepository<EstadoPerfil, Long> {

    Optional<EstadoPerfil> findByIdPostulante(Long idPostulante);
}
