package es.altia.bne.postulante.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.Postulacion;

@Repository
public interface PostulacionesRepository extends JpaRepository<Postulacion, Long> {

    @Query("""
        SELECT COUNT(p)
        FROM Postulacion p
        WHERE p.idPostulante = :idPostulante
    """)
    int countPostuladas(Long idPostulante);

    @Query("""
        SELECT COUNT(p)
        FROM Postulacion p
        WHERE p.idPostulante = :idPostulante
          AND p.estado = 'CASADA'
    """)
    int countCasadas(Long idPostulante);

    @Query("""
        SELECT COUNT(p)
        FROM Postulacion p
        WHERE p.idPostulante = :idPostulante
          AND p.estado = 'EN_PROCESO'
    """)
    int countEnProceso(Long idPostulante);

    @Query("""
        SELECT COUNT(v)
        FROM PostulacionVista v
        WHERE v.idPostulante = :idPostulante
    """)
    int countVistas(Long idPostulante);
}
