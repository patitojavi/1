package es.altia.bne.postulante.domain.repository;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import jakarta.persistence.NoResultException;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la tabla
 * PER_PERSONAS.
 *
 * @author carol.chamblas
 */
public interface DatosPostulanteRepository {

    /**
     * Obtiene los datos de un postulante.
     *
     * @param idSucidPostulanteursal Identificador del postulante.
     * @return PerPersonaDTO Datos del postulante.
     * @throws ResourceNotFoundException Recurso no encontrado.
     * @throws DBAccessException         Error inesperado.
     */
    DatosBasicosDTO obtenerDatosBasicos(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Obtiene los datos de conntacto de un postulante.
     * 
     * @param idPostulante
     * @return
     * @throws NoResultException
     * @throws DBAccessException
     */
    DatosContactoDTO obtenerContacto(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza sólo los datos de Contacto del postulante
     * @param persona
     * @return
     * @throws DBAccessException
     */
    int actualizarContacto(PerPersonas persona) throws DBAccessException;

    /**
     * Obtiene la direccion de un postulante.
     * 
     * @param idPostulante
     * @return Datos de la direccion de un postulante.
     * @throws NoResultException
     * @throws DBAccessException
     */
    DatosDireccionDTO obtenerDireccion(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza sólo los datos de direccion del postulante
     * 
     * @param persona instancia con datos a actualizar
     * @return Número de registros afectados.
     * @throws DBAccessException
     */
    int actualizarDireccion(PerPersonas persona) throws DBAccessException;

    DatosDiscapacidadDTO obtenerDiscapacidad(Long idPostulante) throws NoResultException, DBAccessException;

    Integer obtenerNivelEducacionMax(Long idPostulante) throws NoResultException, DBAccessException;

    Integer obtenerSitioReferencia(Long idPostulante) throws NoResultException, DBAccessException;

    DatosPersonalesDTO obtenerDatosPersonales(Long idPostulante) throws NoResultException, DBAccessException;

    /**
     * Actualiza los dados de discapacidad de un postulante
     * @param discapacidadDTO
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarDiscapacidad(PerPersonas persona) throws DBAccessException;

    /**
     * Actualiza el sitio referente de un postulante (como se entero de este sitio?). Persona solo trae idPostulante e idSitio a modificar
     * @param persona
     * @return numero de registros afectados
     * @throws ResourceNotFoundException
     * @throws ServiceException
     */
    int actualizarSitio(PerPersonas persona) throws DBAccessException;

    /**
     * Busca una persona por tipo de documento y número de documento.
     * 
     * @param tipoDocumento Tipo de documento
     * @param numDocumento Número de documento
     * @return PerPersonas La persona encontrada
     * @throws NoResultException Si no se encuentra la persona
     * @throws DBAccessException Error de acceso a base de datos
     */
    PerPersonas buscarPersonaPorRut(Integer tipoDocumento, String numDocumento) throws NoResultException, DBAccessException;

    /**
     * Busca una persona por tipo de documento, número de documento y dígito verificador.
     * 
     * @param tipoDocumento Tipo de documento
     * @param numDocumento Número de documento
     * @param digitoVerificador Dígito verificador
     * @return PerPersonas La persona encontrada
     * @throws NoResultException Si no se encuentra la persona
     * @throws DBAccessException Error de acceso a base de datos
     */
    PerPersonas buscarPersonaPorRutConDv(Integer tipoDocumento, String numDocumento, String digitoVerificador) throws NoResultException, DBAccessException;

    /**
     * Busca una persona por tipo de documento y número de documento cuando el dígito verificador está vacío o es null.
     * 
     * @param tipoDocumento Tipo de documento
     * @param numDocumento Número de documento
     * @return PerPersonas La persona encontrada
     * @throws NoResultException Si no se encuentra la persona
     * @throws DBAccessException Error de acceso a base de datos
     */
    PerPersonas buscarPersonaPorRutSinDv(Integer tipoDocumento, String numDocumento) throws NoResultException, DBAccessException;

    PerPersonas obtenerPersonaCompleta(Long idPostulante)
            throws NoResultException, DBAccessException;;

}
