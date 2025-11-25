package es.altia.bne.postulante.domain.repository;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de las tablas comunes
 * BNE_SIT_LABORAL.
 *
 * @author carol.chamblas
 */
public interface EntidadReferenciaRepository {

    <T> T getReference(Class<T> clazz, Object id);
//    /**
//     * Obtiene  BneSitLaboralpor su id
//     * @param idBneSitLaboral
//     * @return 
//     * @throws DataValidationException
//     * @throws ResourceNotFoundException
//     * @throws ServiceException
//     */
//    BneSitLaboral obtenerBneSitLaboral(Long idBneSitLaboral) throws NoResultException, DBAccessException;
//
//    /**
//     * Obtiene objeto pocional de BneSitLaboralpor su id
//     * @param idBneSitLaboral
//     * @return 
//     * @throws DataValidationException
//     * @throws ResourceNotFoundException
//     * @throws ServiceException
//     */
//    Optional<BneSitLaboral> findById(Long id) throws NoResultException, DBAccessException;
}
