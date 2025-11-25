package es.altia.bne.postulante.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.HomePostulanteDTO;
import es.altia.bne.postulante.application.service.HomePostulanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Controlador REST encargado de exponer los endpoints del Home del Postulante.
 *
 * Implementa la historia de usuario:
 * "Visualización del Home del Postulante (GET)" y
 * "Actualización de indicadores del Home (PUT)".
 *
 * @author nelson.neculhueque
 */
@RestController
@RequestMapping("/postulantes")
@Tag(name = "Postulantes - Home", description = "Endpoints para la página principal del postulante en BNE 2.0")
@RequiredArgsConstructor
@Log4j2
public class HomePostulanteController {

    private final HomePostulanteService homePostulanteService;

    // ===========================================================
    // === GET /postulantes/home =================================
    // ===========================================================

    @Operation(
        summary = "Obtiene la información principal del Home del postulante",
        description = "Retorna la información consolidada del postulante, incluyendo estadísticas, datos personales, curriculares y estado de perfil."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Información del Home obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = HomePostulanteDTO.class))
    )
    @ApiResponse(responseCode = "400", description = "Identificador inválido o datos inconsistentes")
    @ApiResponse(responseCode = "404", description = "No se encontró información para el postulante")
    @ApiResponse(responseCode = "500", description = "Error interno al consolidar los datos del Home")
    @GetMapping("/home")
    public ResponseEntity<HomePostulanteDTO> obtenerHome(
            @RequestParam("idPostulante") Long idPostulante)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteController] GET /postulantes/home solicitado para idPostulante={}", idPostulante);
        HomePostulanteDTO dto = homePostulanteService.obtenerHome(idPostulante);
        return ResponseEntity.ok(dto);
    }

    // ===========================================================
    // === PUT /postulantes/home ================================
    // ===========================================================

    @Operation(
        summary = "Actualiza el estado general del Home del postulante",
        description = "Permite registrar cambios de indicadores visibles en el Home (por ejemplo: consentimiento CV, ferias activas, etc.)."
    )
    @ApiResponse(responseCode = "200", description = "Actualización realizada correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos en el objeto HomePostulanteDTO")
    @ApiResponse(responseCode = "404", description = "Postulante no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno al actualizar el estado del Home")
    @PutMapping("/home")
    public ResponseEntity<Integer> actualizarHome(
            @RequestParam("idPostulante") Long idPostulante,
            @RequestBody HomePostulanteDTO dto)
            throws ResourceNotFoundException, ServiceException {

        log.info("[HomePostulanteController] PUT /postulantes/home solicitado para idPostulante={}", idPostulante);
        int resultado = homePostulanteService.actualizarEstadoHome(dto);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
    
    // ===========================================================
    // === GET /home/test PRUEBA================================
    // ===========================================================

    @GetMapping("/home/test")
    public ResponseEntity<HomePostulanteDTO> probarHome() {
        // Simular ID de postulante
        Long idPostulante = 1L;

        try {
            HomePostulanteDTO dto = homePostulanteService.obtenerHome(idPostulante);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}


