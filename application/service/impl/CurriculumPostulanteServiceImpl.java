package es.altia.bne.postulante.application.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.BneOcupacionesDTO;
import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralArchivosDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.dto.DemVehiculosDTO;
import es.altia.bne.postulante.application.service.CurriculumPostulanteService;
import es.altia.bne.postulante.application.service.DatosPostulanteService;
import es.altia.bne.postulante.application.service.DemCondLabService;
import es.altia.bne.postulante.application.service.DemTitulacionesService;
import es.altia.bne.postulante.application.service.DemVehiculosService;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneOcupaciones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboralArchivos;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemPresentacion;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemReferenciasLaborales;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemExpLaboralJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemPresentacionJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemReferenciasLaboralesJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CurriculumPostulanteServiceImpl implements CurriculumPostulanteService {

    private final DatosPostulanteService datosPostulanteService;
    private final DemCondLabService demCondLabService;
    private final DemTitulacionesService demTitulacionesService;
    private final DemVehiculosService demVehiculosService;
    private final DemPresentacionJpaRepository demPresentacionRepository;
    private final DemExpLaboralJpaRepository demExpLaboralRepository;
    private final DemReferenciasLaboralesJpaRepository demReferenciasLaboralesRepository;

    @Override
    @Transactional(readOnly = true)
    public CurriculumVitaeDTO obtenerCurriculum(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        DatosPersonalesDTO datosPersonales = datosPostulanteService.obtenerDatosPersonales(idPostulante);

        DemCondLabDTO condicionLaboral = obtenerCondicionLaboral(idPostulante);

        DemPresentacionDTO resumenPerfil = demPresentacionRepository
                .findFirstByPerPersonasIdOrderByFecModifDesc(idPostulante)
                .map(this::mapPresentacion)
                .orElse(null);

        List<DemExpLaboralDTO> experienciasLaborales = demExpLaboralRepository
                .findByPerPersonasIdOrderByFecIniDesc(idPostulante)
                .stream()
                .map(this::mapExperienciaLaboral)
                .collect(Collectors.toList());

        List<DemReferenciasLaboralesDTO> referenciasLaborales = demReferenciasLaboralesRepository
                .findByPerPersonasIdOrderByIdAsc(idPostulante)
                .stream()
                .map(this::mapReferenciaLaboral)
                .collect(Collectors.toList());

        List<DemTitulacionesDTO> titulaciones = obtenerTitulaciones(idPostulante);
        List<DemVehiculosDTO> vehiculos = obtenerVehiculos(idPostulante);

        return CurriculumVitaeDTO.builder()
                .datosPersonales(datosPersonales)
                .condicionLaboral(condicionLaboral)
                .resumenPerfil(resumenPerfil)
                .experienciasLaborales(experienciasLaborales)
                .referenciasLaborales(referenciasLaborales)
                .titulaciones(titulaciones)
                .vehiculos(vehiculos)
                .build();
    }

    private DemCondLabDTO obtenerCondicionLaboral(Long idPostulante)
            throws DataValidationException, ServiceException {
        try {
            return demCondLabService.obtenerCondicionActual(idPostulante);
        } catch (ResourceNotFoundException ex) {
            log.info("No se encontró condición laboral para el postulante {}", idPostulante);
            return null;
        }
    }

    private List<DemTitulacionesDTO> obtenerTitulaciones(Long idPostulante) throws ServiceException, DataValidationException {
        try {
            return Optional.ofNullable(demTitulacionesService.obtenerTitulaciones(idPostulante))
                    .orElse(Collections.emptyList());
        } catch (ResourceNotFoundException ex) {
            log.info("El postulante {} no registra titulaciones", idPostulante);
            return Collections.emptyList();
        }
    }

    private List<DemVehiculosDTO> obtenerVehiculos(Long idPostulante) throws ServiceException, DataValidationException {
        try {
            return Optional.ofNullable(demVehiculosService.obtenerVehiculos(idPostulante))
                    .orElse(Collections.emptyList());
        } catch (ResourceNotFoundException ex) {
            log.info("El postulante {} no registra vehículos", idPostulante);
            return Collections.emptyList();
        }
    }

    private DemPresentacionDTO mapPresentacion(DemPresentacion entity) {
        DemPresentacionDTO dto = new DemPresentacionDTO();
        dto.setId(entity.getId());
        PerPersonas persona = entity.getPerPersonas();
        dto.setIdPostulante(persona != null ? persona.getId() : null);
        dto.setTextoCarta(entity.getTexto());
        dto.setFechaAlta(entity.getFecAlta());
        dto.setFechaModif(entity.getFecModif());
        return dto;
    }

    private DemExpLaboralDTO mapExperienciaLaboral(DemExpLaboral entity) {
        DemExpLaboralDTO dto = new DemExpLaboralDTO();
        dto.setId(entity.getId());
        PerPersonas persona = entity.getPerPersonas();
        dto.setIdPostulante(persona != null ? persona.getId() : null);

        BneOcupaciones ocupacion = entity.getBneOcupaciones();
        if (ocupacion != null) {
            BneOcupacionesDTO ocupacionDTO = new BneOcupacionesDTO();
            ocupacionDTO.setId(ocupacion.getId());
            ocupacionDTO.setCodigo(ocupacion.getCodigo());
            ocupacionDTO.setNombre(ocupacion.getNombre());
            dto.setBneOcupaciones(ocupacionDTO);
        }

        BneRegiones region = entity.getBneRegiones();
        if (region != null) {
            dto.setIdRegion(region.getId());
            dto.setNombreRegion(region.getNombre());
        }

        dto.setRazonSocial(entity.getRazonSocial());
        dto.setFecIni(entity.getFecIni());
        dto.setFecFin(entity.getFecFin());
        dto.setNumMeses(entity.getNumMeses());
        dto.setSueldo(entity.getSueldo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCodFichero(entity.getCodFichero());
        dto.setFecAlta(entity.getFecAlta());
        dto.setFecModif(entity.getFecModif());
        dto.setReferencias(entity.getReferencias());
        dto.setOtrasOcupaciones(entity.getOtrasOcupaciones());
        dto.setActualidad(entity.getFecFin() == null);

        DemExpLaboralArchivos archivo = entity.getDemExpLaboralArchivos();
        if (archivo != null) {
            DemExpLaboralArchivosDTO archivoDTO = new DemExpLaboralArchivosDTO();
            archivoDTO.setId(archivo.getId());
            archivoDTO.setIdDemExpLaboral(entity.getId());
            archivoDTO.setArchivo(archivo.getArchivo());
            archivoDTO.setNombre(archivo.getNombre());
            dto.setDemExpLaboralArchivos(archivoDTO);
        }

        return dto;
    }

    private DemReferenciasLaboralesDTO mapReferenciaLaboral(DemReferenciasLaborales entity) {
        DemReferenciasLaboralesDTO dto = new DemReferenciasLaboralesDTO();
        dto.setId(entity.getId());
        PerPersonas persona = entity.getPerPersonas();
        dto.setIdPostulante(persona != null ? persona.getId() : null);
        dto.setNombre(entity.getNombre());
        dto.setPuesto(entity.getPuesto());
        dto.setEmpresa(entity.getEmpresa());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}