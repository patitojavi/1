package es.altia.bne.postulante.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;

// ==== DTOs ====
import es.altia.bne.postulante.application.dto.HomePostulanteDTO;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.dto.DemVehiculosDTO;

// ==== Servicios ====
import es.altia.bne.postulante.application.service.HomePostulanteService;
import es.altia.bne.postulante.application.service.DatosPostulanteService;
import es.altia.bne.postulante.application.service.DatosCurricularesService;
import es.altia.bne.postulante.application.service.DemCondLabService;
import es.altia.bne.postulante.application.service.DemTitulacionesService;
import es.altia.bne.postulante.application.service.DemVehiculosService;

/**
 * Implementación del servicio {@link HomePostulanteService}.
 *
 * Consolida la información principal del Home del postulante (BNE 2.0),
 * integrando datos curriculares, personales, laborales y educativos.
 *
 * Implementa la historia de usuario:
 * "Visualización del Home del Postulante (GET)".
 *
 * @author nelson.neculhueque
 */
@Service
@Log4j2
public class HomePostulanteServiceImpl implements HomePostulanteService {

    private final DatosCurricularesService datosCurricularesService;
    private final DemCondLabService demCondLabService;
    private final DemTitulacionesService demTitulacionesService;
    private final DemVehiculosService demVehiculosService;
    private final DatosPostulanteService datosPostulanteService;

    public HomePostulanteServiceImpl(
            DatosCurricularesService datosCurricularesService,
            DemCondLabService demCondLabService,
            DemTitulacionesService demTitulacionesService,
            DemVehiculosService demVehiculosService,
            DatosPostulanteService datosPostulanteService) {

        this.datosCurricularesService = datosCurricularesService;
        this.demCondLabService = demCondLabService;
        this.demTitulacionesService = demTitulacionesService;
        this.demVehiculosService = demVehiculosService;
        this.datosPostulanteService = datosPostulanteService;
    }

    // ================================================================
    // === MÉTODO PRINCIPAL: OBTENER HOME POSTULANTE ==================
    // ================================================================
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public HomePostulanteDTO obtenerHome(Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteServiceImpl] Iniciando obtención del Home para postulante ID={}", idPostulante);

        if (idPostulante == null || idPostulante <= 0) {
            throw new DataValidationException("El identificador del postulante no es válido.");
        }

        try {

            // =============================================================
            // === BLOQUE DE PRUEBA LOCAL (sin BD) =========================
            // =============================================================
            if (idPostulante == 999L) {
                log.warn("[HomePostulanteServiceImpl] Cargando datos simulados (modo prueba).");

                DatosPersonalesDTO persona = new DatosPersonalesDTO(
                    new DatosBasicosDTO(1L, "12345678-9", "Nelson", "Neculhueque", "Soto",
                        new java.util.Date(), "M", 152, 1, "9", 1, true),
                    new DatosContactoDTO(1L, "+56911112222", "+56933334444", true, "nelson@correo.cl"),
                    new DatosDireccionDTO(1L, 13, "Región Metropolitana", 13101, "Santiago",
                        "Av. Siempre Viva", "742", "Depto 4B", "Centro", true),
                    new DatosDiscapacidadDTO(1L, "S", "N", true),
                    5
                );

                DemCondLabDTO condicionLaboral = new DemCondLabDTO();
                DemTitulacionesDTO nivelEducacional = new DemTitulacionesDTO(1L, idPostulante, null, "Ingeniería Informática", null, "UCT", null, "2019", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                DemExpLaboralDTO experiencia = new DemExpLaboralDTO();
                experiencia.setId(1L);
                experiencia.setIdPostulante(idPostulante);
                experiencia.setRazonSocial("Altia");
                experiencia.setOtrasOcupaciones("Desarrollador Java");
                experiencia.setDescripcion("Backend Developer");

                return HomePostulanteDTO.builder()
                        .datosPersonales(persona)
                        .condicionLaboralActual(condicionLaboral)
                        .nivelEducacional(nivelEducacional)
                        .ultimaExperienciaLaboral(experiencia)
                        .perfilCompleto(true)
                        .consentimientoCV(true)
                        .feriasActivas(false)
                        .recibeFondoCesantia(false)
                        .encuestaPendiente(false)
                        .invitacionesPendientes(List.of())
                        .totalPostulaciones(10L)
                        .postulacionesEnProceso(3L)
                        .postulacionesFinalizadas(7L)
                        .empresasQueVieronCv(2L)
                        .build();
            }

            // =============================================================
            // === MODO NORMAL (usa servicios reales) ======================
            // =============================================================

            PerPersonaDTO persona = datosPostulanteService.obtenerPersonaCompleta(idPostulante);
            DemCondLabDTO condicionLaboral = demCondLabService.obtenerCondicionActual(idPostulante);
            List<DemTitulacionesDTO> titulaciones = demTitulacionesService.obtenerTitulaciones(idPostulante);
            DemExpLaboralDTO experiencia = datosCurricularesService.obtenerExperienciaLaboral(idPostulante);
            List<DemVehiculosDTO> vehiculos = demVehiculosService.obtenerVehiculos(idPostulante);

            HomePostulanteDTO dto = HomePostulanteDTO.builder()
                    .datosPersonales(mapDatosPersonales(persona))
                    .condicionLaboralActual(condicionLaboral)
                    .nivelEducacional(!titulaciones.isEmpty() ? titulaciones.get(0) : null)
                    .ultimaExperienciaLaboral(experiencia)
                    .perfilCompleto(validarPerfilCompleto(persona, titulaciones, experiencia))
                    .consentimientoCV(Boolean.TRUE.equals(persona.getCvCompleto()))
                    .feriasActivas(false)
                    .recibeFondoCesantia(Boolean.TRUE.equals(persona.getEsFcs()))
                    .encuestaPendiente(false)
                    .invitacionesPendientes(List.of())
                    .totalPostulaciones(0L)
                    .postulacionesEnProceso(0L)
                    .postulacionesFinalizadas(0L)
                    .empresasQueVieronCv(0L)
                    .build();

            return dto;

        } catch (ResourceNotFoundException rnfe) {
            log.error("[HomePostulanteServiceImpl] No se encontró información para el postulante ID={}", idPostulante, rnfe);
            throw rnfe;
        } catch (Exception ex) {
            log.error("[HomePostulanteServiceImpl] Error al obtener información del Home (ID={})", idPostulante, ex);
            throw new ServiceException("Error al consolidar la información del Home del postulante", ex);
        }
    }


    // ================================================================
    // === MÉTODO SECUNDARIO: ACTUALIZAR HOME =========================
    // ================================================================

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public int actualizarEstadoHome(HomePostulanteDTO homePostulanteDTO)
            throws ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteServiceImpl] Actualizando estado del Home del postulante");

        if (homePostulanteDTO == null) {
            throw new ServiceException("El objeto HomePostulanteDTO no puede ser nulo.");
        }

        try {
            // TODO: Implementar persistencia de indicadores en BD
            log.debug("[HomePostulanteServiceImpl] Estado del Home actualizado correctamente");
            return 1;
        } catch (Exception ex) {
            log.error("[HomePostulanteServiceImpl] Error al actualizar estado del Home del postulante", ex);
            throw new ServiceException("Error al actualizar el estado del Home del postulante", ex);
        }
    }

    // ================================================================
    // === MÉTODOS AUXILIARES PRIVADOS ================================
    // ================================================================

    private DatosPersonalesDTO mapDatosPersonales(PerPersonaDTO persona) {
        if (persona == null) {
            return null;
        }

        // === Datos básicos ===
        DatosBasicosDTO datosBasicos = new DatosBasicosDTO(
                persona.getId(),
                persona.getNumDocumento(),
                persona.getNombre(),
                persona.getApe1(),
                persona.getApe2(),
                persona.getFecNac(),
                persona.getSexo(),
                persona.getNacionalidad(),
                persona.getEstadoCivil(),
                persona.getDigitoVerificador(),
                persona.getTipoDocumento(),
                persona.getIndMostrarDatospostulante()
        );

        // === Contacto ===
        DatosContactoDTO contacto = new DatosContactoDTO(
                persona.getId(),
                persona.getTlfnoNotif(),
                persona.getTlfnoAlt(),
                persona.getNotifSms(),
                persona.getEmail()
        );

        // === Dirección ===
        DatosDireccionDTO direccion = new DatosDireccionDTO();
        direccion.setIdPostulante(persona.getId());

        if (persona.getBneRegiones() != null) {
            direccion.setIdRegion(persona.getBneRegiones().getId());
            direccion.setNombreRegion(persona.getBneRegiones().getNombre());
        }
        if (persona.getBneComunas() != null) {
            direccion.setIdComuna(persona.getBneComunas().getId());
            direccion.setNombreComuna(persona.getBneComunas().getNombre());
        }

        direccion.setCalle(persona.getCalle());
        direccion.setNumero(persona.getNumero());
        direccion.setCasa(persona.getCasa());
        direccion.setPoblacion(persona.getPoblacion());
        direccion.setIndMostrarDiccion(persona.getIndMostrarDiccion());

        // === Discapacidad ===
        DatosDiscapacidadDTO discapacidad = new DatosDiscapacidadDTO();
        discapacidad.setGradoDiscapacidad("S".equalsIgnoreCase(persona.getIndDiscapacidad()));
        discapacidad.setMostrarDiscapacidad(persona.getMostrarDiscapacidad());

        // === Construcción final ===
        return new DatosPersonalesDTO(
                datosBasicos,
                contacto,
                direccion,
                discapacidad,
                persona.getSitio()
        );
    }

    private boolean validarPerfilCompleto(PerPersonaDTO persona,
                                          List<DemTitulacionesDTO> titulaciones,
                                          DemExpLaboralDTO experiencia) {

        return persona != null
                && Boolean.TRUE.equals(persona.getCvCompleto())
                && experiencia != null
                && titulaciones != null && !titulaciones.isEmpty();
    }
    
}
