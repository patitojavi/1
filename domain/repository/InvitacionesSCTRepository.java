package es.altia.bne.postulante.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.InvitacionSCT;

public interface InvitacionesSCTRepository extends JpaRepository<InvitacionSCT, Long> {

    @Query("""
        SELECT i
        FROM InvitacionSCT i
        WHERE i.idPostulante = :idPostulante
          AND i.estado = 'PENDIENTE'
    """)
    List<InvitacionSCT> findPendientesByPostulante(Long idPostulante);
}
