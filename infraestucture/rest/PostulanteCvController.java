package es.altia.bne.postulante.infraestucture.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.CvProfileDTO;
import es.altia.bne.postulante.application.dto.DatosCurricularesDTO;
import es.altia.bne.postulante.application.service.CvProfileService;
import es.altia.bne.postulante.application.service.DatosCurricularesService;

@RestController
@RequestMapping("/v1/postulantes")
@RequiredArgsConstructor
@Validated
@Log4j2
@Tag(name = "Postulantes", description = "Operaciones relacionadas al postulante")
public class PostulanteCvController {

  private final CvProfileService cvProfileService;
  private final DatosCurricularesService datosCurricularesService;

  @Operation(
      summary = "Obtiene el perfil CV completo del postulante",
      description = "Datos básicos, contacto, dirección, experiencia, educación, etc.",
      responses = {
          @ApiResponse(responseCode = "200", description = "OK",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = CvProfileDTO.class),
                  examples = @ExampleObject(value = """
                    {
                      "idPostulante": 123,
                      "datosBasicos": { "id": 123, "nombre": "Nelson", "ape1": "Neculhueque" },
                      "contacto": { "telefonoNotificaciones": "+56 9 1111 1111" },
                      "direccion": { "ciudad": "Temuco", "region": "Araucanía" },
                      "experiencias": [],
                      "educacion": [],
                      "habilidades": [],
                      "idiomas": []
                    }
                  """))),
          @ApiResponse(responseCode = "404", description = "No encontrado")
      })
  @GetMapping("/{id}/cv-profile")
  public ResponseEntity<CvProfileDTO> getCvProfile(
      @Parameter(description = "ID del postulante", example = "123") 
      @PathVariable @Positive(message = "El ID debe ser mayor que 0") Long id) {

    log.info("Obteniendo perfil CV para postulante con ID: {}", id);
    return ResponseEntity.ok(cvProfileService.getCvProfile(id));
  }

  @Operation(
      summary = "Obtiene los datos curriculares completos del postulante",
      description = "Incluye datos básicos, contacto, dirección, experiencia, educación, habilidades, etc.",
      responses = {
          @ApiResponse(responseCode = "200", description = "OK",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = DatosCurricularesDTO.class))),
          @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "404", description = "No encontrado",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "500", description = "Error interno del servidor",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)))
      })
  @GetMapping("/{id}/curriculum")
  public ResponseEntity<DatosCurricularesDTO> getDatosCurriculares(
      @Parameter(description = "ID del postulante", example = "123") 
      @PathVariable @Positive(message = "El ID debe ser mayor que 0") Long id)
      throws DataValidationException, ResourceNotFoundException, ServiceException {

    log.info("Obteniendo datos curriculares para postulante con ID: {}", id);
    var datosCurriculares = datosCurricularesService.obtenerDatosCurriculares(id);
    return ResponseEntity.ok(datosCurriculares);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    log.warn("Recurso no encontrado: {}", ex.getMessage());
    var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(DataValidationException.class)
  public ResponseEntity<ErrorResponse> handleDataValidationException(DataValidationException ex) {
    log.warn("Error de validación: {}", ex.getMessage());
    var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
    log.error("Error de servicio: {}", ex.getMessage(), ex);
    var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    log.error("Error no controlado: {}", ex.getMessage(), ex);
    var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
