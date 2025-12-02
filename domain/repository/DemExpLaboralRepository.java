package es.altia.bne.postulante.domain.repository;

import java.util.List;
import java.util.Optional;

import es.altia.bne.postulante.domain.model.DemExpLaboral;

public interface DemExpLaboralRepository {
    
    /**
     * Encuentra todas las experiencias laborales de un postulante ordenadas por fecha de inicio descendente
     * 
     * @param idPostulante ID del postulante
     * @return Lista de experiencias laborales
     */
    List<DemExpLaboral> findByIdPostulanteOrderByFecIniDesc(Long idPostulante);
    
    /**
     * Verifica si existe una experiencia laboral para un postulante específico
     * 
     * @param id ID de la experiencia laboral
     * @param idPostulante ID del postulante
     * @return true si existe, false si no
     */
    boolean existsByIdAndIdPostulante(Long id, Long idPostulante);

    /**
     * Guarda una experiencia laboral
     * 
     * @param entity La experiencia laboral a guardar
     * @return La experiencia laboral guardada
     */
    DemExpLaboral save(DemExpLaboral entity);

    /**
     * Elimina una experiencia laboral
     * 
     * @param entity La experiencia laboral a eliminar
     */
    void delete(DemExpLaboral entity);

    /**
     * Busca una experiencia laboral por su ID
     * 
     * @param id ID de la experiencia laboral
     * @return Optional con la experiencia laboral si existe
     */
    Optional<DemExpLaboral> findById(Long id);

    /**
     * Busca todas las experiencias laborales
     * 
     * @return Lista de todas las experiencias laborales
     */
    List<DemExpLaboral> findAll();
}