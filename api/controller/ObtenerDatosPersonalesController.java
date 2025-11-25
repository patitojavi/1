package es.altia.bne.postulante.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.ObtenerDatosPersonalesResponseDTO;
import es.altia.bne.postulante.application.service.ObtenerDatosPersonalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Controlador REST para la Historia de Usuario: Obtención de Datos Personales (GET)
 * 
 * Proporciona endpoints GET separados que permiten a los postulantes consultar sus datos 
 * personales por secciones: datos básicos, contacto, direcciones, discapacidad y sitio referencia.
 * 
 * @author assistant
 */
@RestController
@RequestMapping("/postulante")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Obtener Datos Personales", 
     description = "Endpoints separados para obtener datos personales del postulante por secciones")
public class ObtenerDatosPersonalesController {

    private final ObtenerDatosPersonalesService obtenerDatosPersonalesService;

    // ==================== ENDPOINT 1: DATOS BÁSICOS ====================

    /**
     * Obtiene los datos básicos de identificación del postulante.
     * 
     * @param idPostulante ID único del postulante
     * @return ResponseEntity con DatosBasicosDTO
     */
    @Operation(
        summary = "Obtener datos básicos del postulante",
        description = "Retorna información fundamental de identificación: nombre, apellidos, documento, etc."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Datos obtenidos exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DatosBasicosDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "ID inválido o nulo"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idPostulante}/datosBasicos")
    public ResponseEntity<?> obtenerDatosBasicos(
            @Parameter(description = "ID del postulante", required = true, example = "123")
            @PathVariable Long idPostulante) {

        log.info("GET /postulante/{}/datosBasicos", idPostulante);

        try {
            // Obtener datos básicos desde el servicio
            ObtenerDatosPersonalesResponseDTO datosCompletos = 
                obtenerDatosPersonalesService.obtenerDatosPersonalesCompletos(idPostulante);
            
            return ResponseEntity.ok(datosCompletos.getDatosBasicos());

        } catch (es.altia.bne.common.exception.validation.DataValidationException e) {
            log.error("Validación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (es.altia.bne.common.exception.ResourceNotFoundException e) {
            log.error("No encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }

    // ==================== ENDPOINT 2: DATOS DE CONTACTO ====================

    /**
     * Obtiene los datos de contacto del postulante.
     * 
     * @param idPostulante ID único del postulante
     * @return ResponseEntity con DatosContactoDTO
     */
    @Operation(
        summary = "Obtener datos de contacto del postulante",
        description = "Retorna información de contacto: email, teléfono, etc."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Datos obtenidos exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DatosContactoDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "ID inválido o nulo"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idPostulante}/datosContacto")
    public ResponseEntity<?> obtenerDatosContacto(
            @Parameter(description = "ID del postulante", required = true, example = "123")
            @PathVariable Long idPostulante) {

        log.info("GET /postulante/{}/datosContacto", idPostulante);

        try {
            ObtenerDatosPersonalesResponseDTO datosCompletos = 
                obtenerDatosPersonalesService.obtenerDatosPersonalesCompletos(idPostulante);
            
            return ResponseEntity.ok(datosCompletos.getDatosContacto());

        } catch (es.altia.bne.common.exception.validation.DataValidationException e) {
            log.error("Validación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (es.altia.bne.common.exception.ResourceNotFoundException e) {
            log.error("No encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }

    // ==================== ENDPOINT 3: DIRECCIONES ====================

    /**
     * Obtiene las direcciones del postulante.
     * 
     * @param idPostulante ID único del postulante
     * @return ResponseEntity con DatosDireccionDTO
     */
    @Operation(
        summary = "Obtener direcciones del postulante",
        description = "Retorna todas las direcciones registradas del postulante"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Datos obtenidos exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DatosDireccionDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "ID inválido o nulo"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idPostulante}/direcciones")
    public ResponseEntity<?> obtenerDirecciones(
            @Parameter(description = "ID del postulante", required = true, example = "123")
            @PathVariable Long idPostulante) {

        log.info("GET /postulante/{}/direcciones", idPostulante);

        try {
            ObtenerDatosPersonalesResponseDTO datosCompletos = 
                obtenerDatosPersonalesService.obtenerDatosPersonalesCompletos(idPostulante);
            
            return ResponseEntity.ok(datosCompletos.getDireccion());

        } catch (es.altia.bne.common.exception.validation.DataValidationException e) {
            log.error("Validación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (es.altia.bne.common.exception.ResourceNotFoundException e) {
            log.error("No encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }

    // ==================== ENDPOINT 4: DATOS DE DISCAPACIDAD ====================

    /**
     * Obtiene los datos de discapacidad del postulante.
     * 
     * @param idPostulante ID único del postulante
     * @return ResponseEntity con DatosDiscapacidadDTO
     */
    @Operation(
        summary = "Obtener datos de discapacidad del postulante",
        description = "Retorna información de discapacidad (si está registrada)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Datos obtenidos exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DatosDiscapacidadDTO.class)
            )
        ),
        @ApiResponse(responseCode = "400", description = "ID inválido o nulo"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idPostulante}/discapacidad")
    public ResponseEntity<?> obtenerDiscapacidad(
            @Parameter(description = "ID del postulante", required = true, example = "123")
            @PathVariable Long idPostulante) {

        log.info("GET /postulante/{}/discapacidad", idPostulante);

        try {
            ObtenerDatosPersonalesResponseDTO datosCompletos = 
                obtenerDatosPersonalesService.obtenerDatosPersonalesCompletos(idPostulante);
            
            return ResponseEntity.ok(datosCompletos.getDiscapacidad());

        } catch (es.altia.bne.common.exception.validation.DataValidationException e) {
            log.error("Validación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (es.altia.bne.common.exception.ResourceNotFoundException e) {
            log.error("No encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }

    // ==================== ENDPOINT 5: SITIO DE REFERENCIA ====================

    /**
     * Obtiene el sitio de referencia (cómo se enteró del sitio).
     * 
     * @param idPostulante ID único del postulante
     * @return ResponseEntity con el ID del sitio de referencia
     */
    @Operation(
        summary = "Obtener sitio de referencia del postulante",
        description = "Retorna cómo se enteró del sitio el postulante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos obtenidos exitosamente"),
        @ApiResponse(responseCode = "400", description = "ID inválido o nulo"),
        @ApiResponse(responseCode = "404", description = "Postulante no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idPostulante}/sitioReferencia")
    public ResponseEntity<?> obtenerSitioReferencia(
            @Parameter(description = "ID del postulante", required = true, example = "123")
            @PathVariable Long idPostulante) {

        log.info("GET /postulante/{}/sitioReferencia", idPostulante);

        try {
            ObtenerDatosPersonalesResponseDTO datosCompletos = 
                obtenerDatosPersonalesService.obtenerDatosPersonalesCompletos(idPostulante);
            
            return ResponseEntity.ok()
                .body("{\"idSitioReferencia\": " + datosCompletos.getIdSitioReferencia() + "}");

        } catch (es.altia.bne.common.exception.validation.DataValidationException e) {
            log.error("Validación fallida: {}", e.getMessage());
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (es.altia.bne.common.exception.ResourceNotFoundException e) {
            log.error("No encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }
}
