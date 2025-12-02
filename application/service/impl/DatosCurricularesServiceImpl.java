package es.altia.bne.postulante.application.service.impl;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.*;
import es.altia.bne.postulante.application.mapper.DemCondLabMapper;
import es.altia.bne.postulante.application.mapper.DemExpLaboralMapper;
import es.altia.bne.postulante.application.mapper.DemConocimientosMapper;
import es.altia.bne.postulante.application.service.DatosCurricularesService;
import es.altia.bne.postulante.domain.repository.DemCondLabRepository;
import es.altia.bne.postulante.domain.repository.DemExpLaboralRepository;
import es.altia.bne.postulante.domain.repository.DemConocimientosRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Implementación del servicio encargado del tratamiento de datos curriculares
 * relacionados con los postulantes.
 *
 * Agrupa y expone operaciones para experiencia profesional, resumen de perfil,
 * experiencia laboral y referencias, además de un método consolidado para
 * obtener todos los datos curriculares (HU getDatosCurriculares v1.1).
 * 
 * @author 
 *  carol.chamblas
 */
@Service
@Log4j2
public class DatosCurricularesServiceImpl implements DatosCurricularesService {
    private final DemCondLabRepository demCondLabRepository;
    private final DemCondLabMapper demCondLabMapper;
    private final DemExpLaboralRepository demExpLaboralRepository;
    private final DemExpLaboralMapper demExpLaboralMapper;
    private final DemConocimientosRepository demConocimientosRepository;
    private final DemConocimientosMapper demConocimientosMapper;

    public DatosCurricularesServiceImpl(
            DemCondLabRepository demCondLabRepository,
            DemCondLabMapper demCondLabMapper,
            DemExpLaboralRepository demExpLaboralRepository,
            DemExpLaboralMapper demExpLaboralMapper,
            DemConocimientosRepository demConocimientosRepository,
            DemConocimientosMapper demConocimientosMapper) {
        this.demCondLabRepository = demCondLabRepository;
        this.demCondLabMapper = demCondLabMapper;
        this.demExpLaboralRepository = demExpLaboralRepository;
        this.demExpLaboralMapper = demExpLaboralMapper;
        this.demConocimientosRepository = demConocimientosRepository;
        this.demConocimientosMapper = demConocimientosMapper;
    }
    @Override
    public Object obtenerExperienciaProfesional(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo experiencia profesional para el postulante con ID: {}", idPostulante);

        var condicion = demCondLabRepository.findByIdPostulante(idPostulante)
                .map(demCondLabMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Condición laboral no encontrada"));

        log.info("Condición laboral: {}", condicion);
        return condicion;
    }

    @Override
    public DemPresentacionDTO obtenerResumenPerfil(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo resumen de perfil para el postulante con ID: {}", idPostulante);

        try {
        
            return new DemPresentacionDTO();
        } catch (Exception e) {
            log.error("Error al obtener resumen de perfil para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener resumen de perfil", e);
        }
    }

    @Override
    public int actualizarResumenPerfil(@Valid DemPresentacionDTO resumenPerfilDTO)
            throws ResourceNotFoundException, ServiceException {
        return 0;
    }

    @Override
    public DemExpLaboralDTO obtenerExperienciaLaboral(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo experiencia laboral para el postulante con ID: {}", idPostulante);

        try {
            
            return new DemExpLaboralDTO();
        } catch (Exception e) {
            log.error("Error al obtener experiencia laboral para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener experiencia laboral", e);
        }
    }

    @Override
    public int actualizarExperienciaLaboral(@Valid DemExpLaboralDTO experienciaLaboralDTO)
            throws ResourceNotFoundException, ServiceException {
        return 0;
    }

    @Override
    public List<DemReferenciasLaboralesDTO> obtenerReferenciasLaborales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo referencias laborales para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener referencias laborales para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener referencias laborales", e);
        }
    }

    @Override
    public int actualizarReferenciasLaborales(@Valid DemReferenciasLaboralesDTO referenciasLaboralesDTO)
            throws ResourceNotFoundException, ServiceException {
        return 0;
    }

    @Override
    public DatosBasicosDTO obtenerDatosBasicos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo datos básicos para el postulante con ID: {}", idPostulante);

        try {
            
            return new DatosBasicosDTO();
        } catch (Exception e) {
            log.error("Error al obtener datos básicos para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener datos básicos", e);
        }
    }

    @Override
    public DatosContactoDTO obtenerDatosContacto(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo datos de contacto para el postulante con ID: {}", idPostulante);

        try {
            
            return new DatosContactoDTO();
        } catch (Exception e) {
            log.error("Error al obtener datos de contacto para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener datos de contacto", e);
        }
    }

    @Override
    public DatosDireccionDTO obtenerDireccion(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo datos de dirección para el postulante con ID: {}", idPostulante);

        try {
            
            return new DatosDireccionDTO();
        } catch (Exception e) {
            log.error("Error al obtener datos de dirección para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener datos de dirección", e);
        }
    }

    @Override
    public List<DemCapacitacionesDTO> obtenerCapacitaciones(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo capacitaciones para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener capacitaciones para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener capacitaciones", e);
        }
    }

    @Override
    public List<DemIdiomasDTO> obtenerIdiomas(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo idiomas para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener idiomas para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener idiomas", e);
        }
    }

    @Override
    public List<DemCertificacionesDTO> obtenerCertificaciones(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo certificaciones para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener certificaciones para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener certificaciones", e);
        }
    }

    @Override
    public List<DemExperienciaEducativaDTO> obtenerExperienciasEducativas(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo experiencias educativas para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener experiencias educativas para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener experiencias educativas", e);
        }
    }

    @Override
    public List<DemExpLaboralDTO> obtenerExperienciasLaborales(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo experiencias laborales para el postulante con ID: {}", idPostulante);

        try {
            var experiencias = demExpLaboralRepository.findByIdPostulanteOrderByFecIniDesc(idPostulante);
            if (experiencias.isEmpty()) {
                throw new ResourceNotFoundException("No se encontraron experiencias laborales para el postulante");
            }
            return demExpLaboralMapper.toDTOList(experiencias);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al obtener experiencias laborales para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener experiencias laborales", e);
        }
    }

    @Override
    public DatosDiscapacidadDTO obtenerDiscapacidad(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo datos de discapacidad para el postulante con ID: {}", idPostulante);

        try {
            
            return new DatosDiscapacidadDTO();
        } catch (Exception e) {
            log.error("Error al obtener datos de discapacidad para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener datos de discapacidad", e);
        }
    }

    @Override
    public List<DemHabilidadesDTO> obtenerHabilidades(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo habilidades para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener habilidades para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener habilidades", e);
        }
    }

    @Override
    public List<DemConocimientosDTO> obtenerConocimientos(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo conocimientos para el postulante con ID: {}", idPostulante);

        try {
            var conocimientos = demConocimientosRepository.findByIdPostulante(idPostulante);
            if (conocimientos.isEmpty()) {
                throw new ResourceNotFoundException("No se encontraron conocimientos para el postulante");
            }
            return demConocimientosMapper.toDTOList(conocimientos);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al obtener conocimientos para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener conocimientos", e);
        }
    }

    @Override
    public List<DemLogrosDTO> obtenerLogros(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        log.info("Obteniendo logros para el postulante con ID: {}", idPostulante);

        try {
            
            return List.of();
        } catch (Exception e) {
            log.error("Error al obtener logros para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener logros", e);
        }
    }

    @Override
    public DatosCurricularesDTO obtenerDatosCurriculares(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        log.info("Obteniendo datos curriculares completos para el postulante con ID: {}", idPostulante);
        
        if (idPostulante == null || idPostulante == 0) {
            throw new DataValidationException("El ID del postulante no puede ser nulo o cero");
        }

        try {
            DatosCurricularesDTO datosCurriculares = new DatosCurricularesDTO();
            
            // 1. Datos básicos y personales
            datosCurriculares.setDatosBasicos(obtenerDatosBasicos(idPostulante));
            datosCurriculares.setContacto(obtenerDatosContacto(idPostulante));
            datosCurriculares.setDireccion(obtenerDireccion(idPostulante));
            
            // 2. Información laboral actual
            datosCurriculares.setCondicionLaboral((DemCondLabDTO)obtenerExperienciaProfesional(idPostulante));
            
            // 3. Resumen y experiencia
            datosCurriculares.setResumenPerfil(obtenerResumenPerfil(idPostulante));
            datosCurriculares.setExperienciasLaborales(obtenerExperienciasLaborales(idPostulante));
            datosCurriculares.setReferenciasLaborales(obtenerReferenciasLaborales(idPostulante));
            
            // 4. Formación y competencias
            datosCurriculares.setCapacitaciones(obtenerCapacitaciones(idPostulante));
            datosCurriculares.setIdiomas(obtenerIdiomas(idPostulante));
            datosCurriculares.setCertificaciones(obtenerCertificaciones(idPostulante));
            datosCurriculares.setExperienciasEducativas(obtenerExperienciasEducativas(idPostulante));
            
            // 5. Información complementaria
            datosCurriculares.setDiscapacidad(obtenerDiscapacidad(idPostulante));
            datosCurriculares.setHabilidades(obtenerHabilidades(idPostulante));
            datosCurriculares.setConocimientos(obtenerConocimientos(idPostulante));
            datosCurriculares.setLogros(obtenerLogros(idPostulante));
            
            log.info("Datos curriculares obtenidos correctamente para el postulante con ID: {}", idPostulante);
            return datosCurriculares;
        } catch (ResourceNotFoundException e) {
            log.error("No se encontraron datos curriculares para el postulante con ID: {}", idPostulante);
            throw e;
        } catch (Exception e) {
            log.error("Error al obtener datos curriculares para el postulante con ID: {}", idPostulante, e);
            throw new ServiceException("Error al obtener datos curriculares", e);
        }
    }


}
