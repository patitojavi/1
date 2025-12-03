package es.altia.bne.postulante.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.CvVisita;

@Repository
public interface CvVisitasRepository extends JpaRepository<CvVisita, Long> {

    @Query("""
        SELECT COUNT(v)
        FROM CvVisita v
        WHERE v.idPostulante = :idPostulante
    """)
    int countVisitasCv(Long idPostulante);
}
