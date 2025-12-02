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
import es.altia.bne.postulante.application.mapper.CurriculumMapper;
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
    private final CurriculumMapper curriculumMapper;

    @Override
    @Transactional(readOnly = true)
    public CurriculumVitaeDTO obtenerCurriculum(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        DatosPersonalesDTO datosPersonales = datosPostulanteService.obtenerDatosPersonales(idPostulante);

        DemCondLabDTO condicionLaboral = obtenerCondicionLaboral(idPostulante);

        DemPresentacionDTO resumenPerfil = demPresentacionRepository
                .findFirstByPerPersonasIdOrderByFecModifDesc(idPostulante)
                .map(curriculumMapper::toPresentacionDto)
                .orElse(null);

        List<DemExpLaboralDTO> experienciasLaborales = demExpLaboralRepository
                .findByPerPersonasIdOrderByFecIniDesc(idPostulante)
                .stream()
                .map(curriculumMapper::toExpLaboralDto)
                .collect(Collectors.toList());

        List<DemReferenciasLaboralesDTO> referenciasLaborales = demReferenciasLaboralesRepository
                .findByPerPersonasIdOrderByIdAsc(idPostulante)
                .stream()
                .map(curriculumMapper::toReferenciaDto)
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


}