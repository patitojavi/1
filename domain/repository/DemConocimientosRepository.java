package es.altia.bne.postulante.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.altia.bne.postulante.domain.model.DemConocimientos;

@Repository
public interface DemConocimientosRepository extends JpaRepository<DemConocimientos, Long> {
    
    /**
     * Encuentra todos los conocimientos de un postulante
     * 
     * @param idPostulante ID del postulante
     * @return Lista de conocimientos
     */
    List<DemConocimientos> findByIdPostulante(Long idPostulante);
    
    /**
     * Verifica si existe un conocimiento para un postulante específico
     * 
     * @param id ID del conocimiento
     * @param idPostulante ID del postulante
     * @return true si existe, false si no
     */
    boolean existsByIdAndIdPostulante(Long id, Long idPostulante);
}