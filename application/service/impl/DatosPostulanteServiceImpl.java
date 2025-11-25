package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.altia.bne.common.constants.Constants;
import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import es.altia.bne.postulante.application.mapper.DatosPostulanteMapper;
import es.altia.bne.postulante.application.service.DatosPostulanteService;
import es.altia.bne.postulante.domain.repository.DatosPostulanteRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del servicio encargado del tratamiento de datos personales
 * relacionados con los postulantes.
 *
 * @author carol.chamblas
 */
@Service
@Log4j2
public class DatosPostulanteServiceImpl implements DatosPostulanteService {
    private final DatosPostulanteRepository datosRepository;

    private final DatosPostulanteMapper datosMapper;

    public DatosPostulanteServiceImpl(DatosPostulanteRepository datosRepository, DatosPostulanteMapper datosMapper) {
        this.datosMapper = datosMapper;
        this.datosRepository = datosRepository;
    }

    @Override
    public DatosBasicosDTO obtenerDatosBasicos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        if ((idPostulante == null) || (idPostulante == 0)) {
            throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
        }

        try {
            return datosRepository.obtenerDatosBasicos(idPostulante);
        } catch (NoResultException nrex) {
            log.error("obtenerDatosBasicos::nrex::" + nrex.getMessage());
            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
        } catch (DBAccessException dbaex) {
            log.error("obtenerDatosBasicos::dbaex::" + dbaex.getMessage());
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }

    }

    @Override
    public DatosContactoDTO obtenerContacto(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if ((idPostulante == null) || (idPostulante == 0)) {
            throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
        }

        try {
            return datosRepository.obtenerContacto(idPostulante);
        } catch (NoResultException nrex) {
            log.error("obtenerContacto::nrex::" + nrex.getMessage());
            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
        } catch (DBAccessException dbaex) {
            log.error("obtenerContacto::dbaex::" + dbaex.getMessage());
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    @Transactional
    public int actualizarContacto(DatosContactoDTO contactoDTO)
            throws ResourceNotFoundException, ServiceException {
        PerPersonas persona = datosMapper.contactoToPersona(contactoDTO);

        try {
            int registrosAfectados = datosRepository.actualizarContacto(persona);

            if (registrosAfectados == 0) {
                log.error("EXCEPCION_RECURSO_NO_ENCONTRADO::" + contactoDTO);
                throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO);
            }
            return registrosAfectados;
        } catch (DBAccessException dbaex) {
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    public DatosDireccionDTO obtenerDireccion(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if ((idPostulante == null) || (idPostulante == 0)) {
            throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
        }

        try {
            return datosRepository.obtenerDireccion(idPostulante);
        } catch (NoResultException nrex) {
            log.error("obtenerDireccion::nrex::" + nrex.getMessage());
            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
        } catch (DBAccessException dbaex) {
            log.error("obtenerDireccion::dbaex::" + dbaex.getMessage());
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    @Transactional
    public int actualizarDireccion(DatosDireccionDTO direccionDTO) throws ResourceNotFoundException, ServiceException {
        PerPersonas persona = datosMapper.direccionToPersona(direccionDTO);

        try {
            int registrosAfectados = datosRepository.actualizarDireccion(persona);

            if (registrosAfectados == 0) {
                throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO);
            }
            return registrosAfectados;
        } catch (DBAccessException dbaex) {
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }

    }

    @Override
    public DatosPersonalesDTO obtenerDatosPersonales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if ((idPostulante == null) || (idPostulante == 0)) {
            throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
        }

        try {
//            DatosPersonalesDTO datos = new DatosPersonalesDTO();
//            datos.setDatosBasicos(this.obtenerDatosBasicos(idPostulante));
//            datos.setDatosContacto(this.obtenerContacto(idPostulante));
//            datos.setDireccion(this.obtenerDireccion(idPostulante));
//            datos.setDiscapacidad(datosRepository.obtenerDiscapacidad(idPostulante));
//            datos.setIdNivelEducacionalMaximo(datosRepository.obtenerNivelEducacionMax(idPostulante));
//            datos.setIdSitioReferencia(datosRepository.obtenerSitioReferencia(idPostulante));
            
            //opcion que con 1 sola consulta trae toda la informacion necesaria, encapsulada en los diferentes objetos DTO para guardardo
            DatosPersonalesDTO datos = datosRepository.obtenerDatosPersonales(idPostulante);
            return datos;
        } catch (NoResultException nrex) {
            log.error("obtenerDatosPersonales::nrex::" + nrex.getMessage());
            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
        } catch (DBAccessException dbaex) {
            log.error("obtenerDireccion::dbaex::" + dbaex.getMessage());
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    @Transactional
    public int actualizarDiscapacidad(@Valid DatosDiscapacidadDTO discapacidadDTO)
            throws ResourceNotFoundException, ServiceException {
        PerPersonas persona = datosMapper.discapacidadToPersona(discapacidadDTO);

        try {
            int registrosAfectados = datosRepository.actualizarDiscapacidad(persona);

            if (registrosAfectados == 0) {
                throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO);
            }
            return registrosAfectados;
        } catch (DBAccessException dbaex) {
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }

    @Override
    @Transactional
    public int actualizarSitioReferente(@Valid PerPersonaDTO personaDTO)
            throws ResourceNotFoundException, ServiceException {
        PerPersonas persona = datosMapper.dtoToEntity(personaDTO);

        try {
            int registrosAfectados = datosRepository.actualizarSitio(persona);

            if (registrosAfectados == 0) {
                throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO);
            }
            return registrosAfectados;
        } catch (DBAccessException dbaex) {
            throw new ServiceException(Constants.EXCEPCION_DEFECTO, dbaex);
        }
    }
    
    @Override
    @Transactional
    public PerPersonaDTO obtenerPersonaCompleta(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
    	
    	if (idPostulante == null || idPostulante <= 0) {
	        throw new DataValidationException(Constants.EXCEPCION_ARGUMENTO_INVALIDO);
	    }

	    try {
	        log.info("[DatosPostulanteServiceImpl] Obteniendo persona completa para idPostulante={}", idPostulante);

	        // Consulta a la capa de repositorio (debe retornar la entidad PerPersonas)
	        PerPersonas entity = datosRepository.obtenerPersonaCompleta(idPostulante);

	        if (entity == null) {
	            log.warn("[DatosPostulanteServiceImpl] No se encontró información para idPostulante={}", idPostulante);
	            throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO);
	        }

	        // Mapeo de entidad a DTO (usando MapStruct)
	        PerPersonaDTO dto = datosMapper.entityToDto(entity);

	        log.debug("[DatosPostulanteServiceImpl] Persona completa obtenida correctamente: {}", dto);
	        return dto;

	    } catch (NoResultException nrex) {
	        log.error("[DatosPostulanteServiceImpl] obtenerPersonaCompleta::nrex::{}", nrex.getMessage());
	        throw new ResourceNotFoundException(Constants.EXCEPCION_RECURSO_NO_ENCONTRADO, nrex);
	    } catch (Exception ex) {
	        log.error("[DatosPostulanteServiceImpl] Error inesperado en obtenerPersonaCompleta", ex);
	        throw new ServiceException("Error al obtener información completa del postulante", ex);
	    }
	}



}
