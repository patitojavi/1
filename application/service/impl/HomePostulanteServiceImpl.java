package es.altia.bne.postulante.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;

// === DTOs ===
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.dto.EstadisticasPostulacionesDTO;
import es.altia.bne.postulante.application.dto.HomePostulanteDTO;
import es.altia.bne.postulante.application.dto.InvitacionSCTDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;

// === Services existentes ===
import es.altia.bne.postulante.application.service.CvVisitasService;
import es.altia.bne.postulante.application.service.DatosCurricularesService;
import es.altia.bne.postulante.application.service.DatosPostulanteService;
import es.altia.bne.postulante.application.service.DemCondLabService;
import es.altia.bne.postulante.application.service.DemTitulacionesService;
import es.altia.bne.postulante.application.service.DemVehiculosService;
import es.altia.bne.postulante.application.service.HomePostulanteService;
import es.altia.bne.postulante.application.service.InvitacionesSCTService;
import es.altia.bne.postulante.application.service.PostulacionEstadisticasService;

// === Nuevos servicios agregados ===
import es.altia.bne.postulante.application.service.EstadoPerfilService;
import es.altia.bne.postulante.application.service.EncuestaProfilingService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class HomePostulanteServiceImpl implements HomePostulanteService {

    private final DatosCurricularesService datosCurricularesService;
    private final DemCondLabService demCondLabService;
    private final DemTitulacionesService demTitulacionesService;
    private final DemVehiculosService demVehiculosService;
    private final DatosPostulanteService datosPostulanteService;

    // === Servicios anteriores ===
    private final PostulacionEstadisticasService postulacionEstadisticasService;
    private final CvVisitasService cvVisitasService;
    private final InvitacionesSCTService invitacionesSCTService;

    // === Nuevos servicios ===
    private final EstadoPerfilService estadoPerfilService;
    private final EncuestaProfilingService encuestaProfilingService;

    public HomePostulanteServiceImpl(
            DatosCurricularesService datosCurricularesService,
            DemCondLabService demCondLabService,
            DemTitulacionesService demTitulacionesService,
            DemVehiculosService demVehiculosService,
            DatosPostulanteService datosPostulanteService,
            PostulacionEstadisticasService postulacionEstadisticasService,
            CvVisitasService cvVisitasService,
            InvitacionesSCTService invitacionesSCTService,
            EstadoPerfilService estadoPerfilService,
            EncuestaProfilingService encuestaProfilingService) {

        this.datosCurricularesService = datosCurricularesService;
        this.demCondLabService = demCondLabService;
        this.demTitulacionesService = demTitulacionesService;
        this.demVehiculosService = demVehiculosService;
        this.datosPostulanteService = datosPostulanteService;

        this.postulacionEstadisticasService = postulacionEstadisticasService;
        this.cvVisitasService = cvVisitasService;
        this.invitacionesSCTService = invitacionesSCTService;

        this.estadoPerfilService = estadoPerfilService;
        this.encuestaProfilingService = encuestaProfilingService;
    }

    // ================================================================
    // === MÉTODO PRINCIPAL ===========================================
    // ================================================================
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public HomePostulanteDTO obtenerHome(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteServiceImpl] Cargando Home del postulante {}", idPostulante);

        if (idPostulante == null || idPostulante <= 0) {
            throw new DataValidationException("El ID del postulante no es válido.");
        }

        try {
            // === Datos del postulante ===
            PerPersonaDTO persona = datosPostulanteService.obtenerPersonaCompleta(idPostulante);
            DemCondLabDTO condicionLaboral = demCondLabService.obtenerCondicionActual(idPostulante);
            List<DemTitulacionesDTO> titulaciones = demTitulacionesService.obtenerTitulaciones(idPostulante);
            DemExpLaboralDTO experiencia = datosCurricularesService.obtenerExperienciaLaboral(idPostulante);

            // === Servicio 1: estadísticas de postulaciones ===
            EstadisticasPostulacionesDTO estadisticas =
                    postulacionEstadisticasService.obtenerEstadisticas(idPostulante);

            // === Servicio 2: visitas al CV ===
            int visitasCv = cvVisitasService.obtenerCantidadVisitas(idPostulante);

            // === Servicio 3: invitaciones pendientes ===
            List<InvitacionSCTDTO> invitaciones =
                    invitacionesSCTService.obtenerInvitacionesPendientes(idPostulante);

            // === Servicio 4: estado de perfil ===
            boolean estadoPerfil = estadoPerfilService.perfilCompleto(idPostulante);

            // === Servicio 5: encuesta profiling ===
            boolean encuestaPendiente = encuestaProfilingService.encuestaPendiente(idPostulante);

            // === Construcción DTO final ===
            return HomePostulanteDTO.builder()
                    .datosPersonales(mapDatosPersonales(persona))
                    .condicionLaboralActual(condicionLaboral)
                    .nivelEducacional(!titulaciones.isEmpty() ? titulaciones.get(0) : null)
                    .ultimaExperienciaLaboral(experiencia)

                    // Indicadores nuevos
                    .perfilCompleto(estadoPerfil)
                    .consentimientoCV(persona != null && Boolean.TRUE.equals(persona.getCvCompleto()))
                    .feriasActivas(false)
                    .recibeFondoCesantia(persona != null && Boolean.TRUE.equals(persona.getEsFcs()))
                    .encuestaPendiente(encuestaPendiente)

                    // Indicadores antiguos
                    .totalPostulaciones((long) estadisticas.getPostuladas())
                    .postulacionesEnProceso((long) estadisticas.getEnProceso())
                    .postulacionesFinalizadas((long) estadisticas.getCasadas())
                    .empresasQueVieronCv((long) visitasCv)
                    .invitacionesPendientes(invitaciones)

                    .build();

        } catch (Exception ex) {
            log.error("[HomePostulanteServiceImpl] Error al procesar Home {}", idPostulante, ex);
            throw new ServiceException("Error al consolidar Home del postulante", ex);
        }
    }

    // ================================================================
    // === ACTUALIZAR ESTADO HOME =====================================
    // ================================================================
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public int actualizarEstadoHome(HomePostulanteDTO homePostulanteDTO)
            throws ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteServiceImpl] Actualizar estado del Home (pendiente implementación)");
        return 1;
    }

    // ================================================================
    // === MAPEO PERSONA → DatosPersonalesDTO ==========================
    // ================================================================
    private DatosPersonalesDTO mapDatosPersonales(PerPersonaDTO p) {
        if (p == null) return null;

        DatosBasicosDTO basico = new DatosBasicosDTO(
                p.getId(), p.getNumDocumento(), p.getNombre(), p.getApe1(), p.getApe2(),
                p.getFecNac(), p.getSexo(), p.getNacionalidad(), p.getEstadoCivil(),
                p.getDigitoVerificador(), p.getTipoDocumento(), p.getIndMostrarDatospostulante()
        );

        DatosContactoDTO contacto = new DatosContactoDTO(
                p.getId(), p.getTlfnoNotif(), p.getTlfnoAlt(),
                p.getNotifSms(), p.getEmail()
        );

        DatosDireccionDTO direccion = new DatosDireccionDTO();
        direccion.setIdPostulante(p.getId());

        if (p.getBneRegiones() != null) {
            direccion.setIdRegion(p.getBneRegiones().getId());
            direccion.setNombreRegion(p.getBneRegiones().getNombre());
        }

        if (p.getBneComunas() != null) {
            direccion.setIdComuna(p.getBneComunas().getId());
            direccion.setNombreComuna(p.getBneComunas().getNombre());
        }

        direccion.setCalle(p.getCalle());
        direccion.setNumero(p.getNumero());
        direccion.setCasa(p.getCasa());
        direccion.setPoblacion(p.getPoblacion());
        direccion.setIndMostrarDiccion(p.getIndMostrarDiccion());

        DatosDiscapacidadDTO discapacidad = new DatosDiscapacidadDTO();
        discapacidad.setGradoDiscapacidad("S".equalsIgnoreCase(p.getIndDiscapacidad()));
        discapacidad.setMostrarDiscapacidad(p.getMostrarDiscapacidad());

        return new DatosPersonalesDTO(
                basico,
                contacto,
                direccion,
                discapacidad,
                p.getSitio()
        );
    }
}
