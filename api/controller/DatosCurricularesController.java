package es.altia.bne.postulante.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.DatosCurricularesDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.service.DatosCurricularesService;
import es.altia.bne.postulante.application.service.DatosCurricularesConsolidadoService;
import es.altia.bne.postulante.application.service.DemCondLabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/datosCurriculares")
@Tag(name = "Curriculum Postulante - Datos Curriculares", description = "Endpoints para obtener y actualizar datos curriculares del postulante: experiencia, educación, idiomas, habilidades, certificaciones, referencias y disponibilidad.")
public class DatosCurricularesController {

    private DatosCurricularesService datosService;
    private DemCondLabService demCondLabService;
    private DatosCurricularesConsolidadoService datosCurricularesConsolidadoService;

    @Operation(summary = "Obtiene los datos de experiencia profesional de un postulante.", description = "Esto incluye: condicion laboral actual, resumen perfil, experiencia laboral y referencias laborales.")
    @GetMapping("/experienciaProfesional/{idPostulante}")
    public ResponseEntity<?> obtenerExperienciaProfesional(@PathVariable Long idPostulante) throws Exception {

        return new ResponseEntity<>(datosService.obtenerExperienciaProfesional(idPostulante), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene la condicion laboral actual de un postulante.")
    @GetMapping("/condicionActual/{idPostulante}")
    public ResponseEntity<?> obtenerCondicionActual(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>(demCondLabService.obtenerCondicionActual(idPostulante), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza los datos de la condicion actual de un postulante.")
    @PatchMapping("/actualizarCondicionActual")
    public ResponseEntity<?> actualizarCondicionActual(@Valid @RequestBody DemCondLabDTO condicionActualDTO) throws Exception {
        demCondLabService.guardarCondicionActual(condicionActualDTO);
        return ResponseEntity.ok().build();
        //new ResponseEntity<>(datosService.actualizarCondicionActual(condicionActualDTO), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene el resumen del perfil de un postulante.")
    @GetMapping("/resumenPerfil/{idPostulante}")
    public ResponseEntity<?> obtenerResumenPerfil(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>(datosService.obtenerResumenPerfil(idPostulante), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza el resumen del perfil de un postulante.")
    @PatchMapping("/actualizarResumenPerfil")
    public ResponseEntity<?> actualizaResumenPerfil(@Valid @RequestBody DemPresentacionDTO resumenPerfilDTO) throws Exception {
        return new ResponseEntity<>(datosService.actualizarResumenPerfil(resumenPerfilDTO), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene la experiencia laboral de un postulante.")
    @GetMapping("/experienciaLaboral/{idPostulante}")
    public ResponseEntity<?> obtenerExperienciaLaboral(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>(datosService.obtenerExperienciaLaboral(idPostulante), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza la experiencia laboral de un postulante..")
    @PatchMapping("/actualizarExperienciaLaboral")
    public ResponseEntity<?> actualizarExperienciaLaboral(@Valid @RequestBody DemExpLaboralDTO experienciaLaboralDTO) throws Exception {
        return new ResponseEntity<>(datosService.actualizarExperienciaLaboral(experienciaLaboralDTO), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene referencias laborales de un postulante.")
    @GetMapping("/referenciasLaborales/{idPostulante}")
    public ResponseEntity<?> obtenerReferenciasLaborales(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>(datosService.obtenerReferenciasLaborales(idPostulante), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza las referenciasLaborales de un postulante.")
    @PatchMapping("/actualizarReferenciasLaborales")
    public ResponseEntity<?> actualizarReferenciasLaborales(@Valid @RequestBody DemReferenciasLaboralesDTO referenciasLaboralesDTO) throws Exception {
        return new ResponseEntity<>(datosService.actualizarReferenciasLaborales(referenciasLaboralesDTO), HttpStatus.OK);
    }

    // ============================================================
    // === GET /datosCurriculares/v1/curriculum/{idPostulante} ===
    // === Historia de Usuario: Obtener Datos Curriculares v1.1 ===
    // ============================================================

    /**
     * Obtiene todos los datos curriculares consolidados de un postulante.
     * 
     * Versión 1.1: Incluye datos personales, experiencia laboral, educación,
     * idiomas, habilidades, certificaciones, referencias, logros y disposición.
     * 
     * @param idPostulante Identificador único del postulante
     * @return DatosCurricularesDTO con información completa del perfil curricular
     */
    @Operation(
        summary = "Obtener Datos Curriculares Consolidados v1.1",
        description = "Retorna todos los datos curriculares del postulante de forma consolidada: "
            + "información personal, experiencia laboral, educación, idiomas, habilidades, "
            + "certificaciones, referencias laborales, logros profesionales y disponibilidad laboral."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Datos curriculares obtenidos exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DatosCurricularesDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "ID de postulante inválido"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al obtener datos curriculares")
    })
    @GetMapping("/v1/curriculum/{idPostulante}")
    public ResponseEntity<DatosCurricularesDTO> obtenerDatosCurricularesV1Consolidado(
            @PathVariable("idPostulante") Long idPostulante) {
        
        var datosCurriculares = datosCurricularesConsolidadoService.obtenerDatosCurriculares(idPostulante);
        
        if (datosCurriculares.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(datosCurriculares.get());
    }

    // ============================================================
    // === Endpoints Anteriores (Mantenimiento) ===
    // ============================================================
}
