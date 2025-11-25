package es.altia.bne.postulante.infraestucture.persistence.repository;

import org.springframework.stereotype.Repository;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.mapper.DatosPostulanteMapper;
import es.altia.bne.postulante.domain.repository.DatosPostulanteRepository;
import es.altia.bne.postulante.infraestucture.persistence.query.PostulanteMicroserviceQueryGroups;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del repositorio para gestionar las operaciones de acceso a
 * datos de la tabla PER_PERSONAS.
 *
 * @author carol.chamblas
 */
@Repository
@Log4j2
public class DatosPostulanteRepositoryImpl extends BaseAbstractRepository implements DatosPostulanteRepository {

    public DatosPostulanteRepositoryImpl(DatosPostulanteMapper datosMapper) {
    }

    @Override
    public DatosBasicosDTO obtenerDatosBasicos(Long idPostulante) throws NoResultException, DBAccessException {

        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerDatosBasicosPostulanteJPQL");
            log.info("obtenerDatosBasicos - JPQL CARGADO: {}", jpql);
            if (jpql == null) {
                throw new DBAccessException("Query 'obtenerDatosBasicosPostulanteJPQL' no fue encontrada en el repositorio. Verifica que application.yml tenga configurado 'spring.jpa.query-locations' y que el archivo jpa-postulante-queries.yml exista en resources/jpa_queries/");
            }
            TypedQuery<DatosBasicosDTO> query = entityManager.createQuery(jpql, DatosBasicosDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerDatosBasicos repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerDatosBasicos repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }

    }

    @Override
    public DatosContactoDTO obtenerContacto(Long idPostulante) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerContactoPostulanteJPQL");
//            log.info("obtenerContacto:" + idPostulante + "::" + jpql);
            TypedQuery<DatosContactoDTO> query = entityManager.createQuery(jpql, DatosContactoDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerContacto repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerContacto repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public int actualizarContacto(PerPersonas persona) throws DBAccessException {
        try {
            log.info("actualizarContacto:" + persona);
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "actualizarContactoJPQL");
            Query query = entityManager.createQuery(jpql);
            query.setParameter("idPostulante", persona.getId());
            query.setParameter("tlfnoNotif", persona.getTlfnoNotif());
            query.setParameter("tlfnoAlt", persona.getTlfnoAlt());
            query.setParameter("notifSms", persona.getNotifSms());
            query.setParameter("email", persona.getEmail());
            return query.executeUpdate();
        } catch (TransactionRequiredException ex) {
            log.error("tre error actualizarContacto::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("error actualizarContacto::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public DatosDireccionDTO obtenerDireccion(Long idPostulante) throws NoResultException, DBAccessException {

        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerDireccionPostulanteJPQL");
//            log.info("obtenerDireccion:" + idPostulante + "::" + jpql);
            TypedQuery<DatosDireccionDTO> query = entityManager.createQuery(jpql, DatosDireccionDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerDireccion repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerDireccion repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public int actualizarDireccion(PerPersonas persona) throws DBAccessException {

        try {
            log.info("actualizarDireccion:" + persona);
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "actualizarDireccionJPQL");
            Query query = entityManager.createQuery(jpql);
//            DIR_EXTRA='', 
            query.setParameter("idPostulante", persona.getId());
            query.setParameter("bneRegion", persona.getBneRegiones());
            query.setParameter("bneComuna", persona.getBneComunas());
            query.setParameter("calle", persona.getCalle());
            query.setParameter("numero", persona.getNumero());
            query.setParameter("casa", persona.getCasa());
            query.setParameter("poblacion", persona.getPoblacion());
            query.setParameter("indMostrarDireccion", persona.getIndMostrarDiccion());
            return query.executeUpdate();
        } catch (TransactionRequiredException ex) {
            log.error("tre error actualizarDireccion::" + ex.getMessage());

            ex.printStackTrace(); // mejor que getMessage()
            Throwable cause = ex.getCause();
            while (cause != null) {
                System.err.println("\n\nCausa: " + cause);
                cause = cause.getCause();
            }

            throw new DBAccessException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("error actualizarDireccion::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public DatosDiscapacidadDTO obtenerDiscapacidad(Long idPostulante) throws NoResultException, DBAccessException {

        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerDiscapacidadJPQL");
            TypedQuery<DatosDiscapacidadDTO> query = entityManager.createQuery(jpql, DatosDiscapacidadDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerDiscapacidad repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerDiscapacidad repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Integer obtenerNivelEducacionMax(Long idPostulante) throws NoResultException, DBAccessException {

        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerNivelEducacionMaxJPQL");
            TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerNivelEducacionMax repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerNivelEducacionMax repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public Integer obtenerSitioReferencia(Long idPostulante) throws NoResultException, DBAccessException {

        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerSitioReferenciaJPQL");
            TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerSitioReferencia repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerSitioReferencia repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public DatosPersonalesDTO obtenerDatosPersonales(Long idPostulante) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "obtenerDatosPersonalesJPQL");
            TypedQuery<DatosPersonalesDTO> query = entityManager.createQuery(jpql, DatosPersonalesDTO.class);
            query.setParameter("idPostulante", idPostulante);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.error("obtenerDatosPersonales repo::nrex::" + nrex.getMessage());
            throw nrex;
        } catch (Exception ex) {
            log.error("obtenerDatosPersonales repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public int actualizarDiscapacidad(PerPersonas persona) throws DBAccessException {
        try {
            log.info("actualizarDiscapacidad:" + persona);
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "actualizarDiscapacidadJPQL");
            Query query = entityManager.createQuery(jpql);
            query.setParameter("idPostulante", persona.getId());
            query.setParameter("indDiscapacidad", persona.getIndDiscapacidad());
            query.setParameter("ajustesPtoTrab", persona.getAjustesPtoTrab());
            query.setParameter("mostrarDiscapacidad", persona.getMostrarDiscapacidad());
            return query.executeUpdate();
        } catch (TransactionRequiredException ex) {
            log.error("tre error actualizarDiscapacidad::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("error actualizarDiscapacidad::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public int actualizarSitio(PerPersonas persona) throws DBAccessException {
        try {
            log.info("actualizarSitio:" + persona);
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "actualizarDireccionJPQL");
            Query query = entityManager.createQuery(jpql);
            query.setParameter("idPostulante", persona.getId());
            query.setParameter("sitio", persona.getSitio());
            return query.executeUpdate();
        } catch (TransactionRequiredException ex) {
            log.error("tre error actualizarSitio::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("error actualizarSitio::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public PerPersonas buscarPersonaPorRut(Integer tipoDocumento, String numDocumento) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "buscarPersonaPorRutJPQL");
            TypedQuery<PerPersonas> query = entityManager.createQuery(jpql, PerPersonas.class);
            query.setParameter("tipoDocumento", tipoDocumento);
            query.setParameter("numDocumento", numDocumento);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.debug("buscarPersonaPorRut repo::nrex:: No se encontró persona con RUT: {}", numDocumento);
            throw nrex;
        } catch (Exception ex) {
            log.error("buscarPersonaPorRut repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public PerPersonas buscarPersonaPorRutConDv(Integer tipoDocumento, String numDocumento, String digitoVerificador) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "buscarPersonaPorRutConDvJPQL");
            TypedQuery<PerPersonas> query = entityManager.createQuery(jpql, PerPersonas.class);
            query.setParameter("tipoDocumento", tipoDocumento);
            query.setParameter("numDocumento", numDocumento);
            query.setParameter("digitoVerificador", digitoVerificador);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.debug("buscarPersonaPorRutConDv repo::nrex:: No se encontró persona con RUT: {} y DV: {}", numDocumento, digitoVerificador);
            throw nrex;
        } catch (Exception ex) {
            log.error("buscarPersonaPorRutConDv repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public PerPersonas buscarPersonaPorRutSinDv(Integer tipoDocumento, String numDocumento) throws NoResultException, DBAccessException {
        try {
            String jpql = loadQuery(PostulanteMicroserviceQueryGroups.POSTULANNTE, "buscarPersonaPorRutSinDvJPQL");
            TypedQuery<PerPersonas> query = entityManager.createQuery(jpql, PerPersonas.class);
            query.setParameter("tipoDocumento", tipoDocumento);
            query.setParameter("numDocumento", numDocumento);
            return query.getSingleResult();
        } catch (NoResultException nrex) {
            log.debug("buscarPersonaPorRutSinDv repo::nrex:: No se encontró persona con RUT: {} sin DV", numDocumento);
            throw nrex;
        } catch (Exception ex) {
            log.error("buscarPersonaPorRutSinDv repo::ex::" + ex.getMessage());
            throw new DBAccessException(ex.getMessage(), ex);
        }
    }

    @Override
    public PerPersonas obtenerPersonaCompleta(Long idPostulante) throws NoResultException, DBAccessException {
        try {
            log.info("[DatosPostulanteRepositoryImpl] Buscando persona completa con ID={}", idPostulante);

            String jpql = """
                    SELECT p
                    FROM PerPersonas p
                    LEFT JOIN FETCH p.bneRegiones r
                    LEFT JOIN FETCH p.bneComunas c
                    LEFT JOIN FETCH p.bneNivelesEducativos n
                    WHERE p.id = :idPostulante
                    """;

            TypedQuery<PerPersonas> query = entityManager.createQuery(jpql, PerPersonas.class);
            query.setParameter("idPostulante", idPostulante);

            PerPersonas persona = query.getSingleResult();

            log.debug("[DatosPostulanteRepositoryImpl] Persona completa encontrada: {}", persona.getNombre());
            return persona;

        } catch (NoResultException nrex) {
            log.warn("[DatosPostulanteRepositoryImpl] No se encontró persona con ID={}", idPostulante);
            throw nrex;
        } catch (Exception ex) {
            log.error("[DatosPostulanteRepositoryImpl] Error al obtener persona completa con ID={}", idPostulante, ex);
            throw new DBAccessException("Error al obtener información completa del postulante", ex);
        }
    }
}
