package es.altia.bne.postulante.infraestucture.persistence.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;

public interface DemTitulacionJpaRepository extends JpaRepository<DemTitulaciones, Long> {
    
    /**
     * Obtiene las titulaciones de una persona ordenadas por nivel educativo descendente
     * @param idPersona ID de la persona
     * @return Lista de titulaciones ordenadas por ID_NIVEL_EDUCATIVO descendente
     */
    @Query("SELECT dt FROM DemTitulaciones dt " +
           "WHERE dt.perPersonas.id = :idPersona " +
           "ORDER BY dt.bneNivelesEducativos.id DESC, dt.fecFin DESC")
    List<DemTitulaciones> findByPerPersonasIdOrderByNivelEducativoDesc(@Param("idPersona") Long idPersona);
}
