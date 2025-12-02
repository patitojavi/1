package es.altia.bne.postulante.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.service.CurriculumDocumentoService;
import es.altia.bne.postulante.application.service.model.CvFormato;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador REST encargado de exponer los endpoints para la
 * generación de documentos curriculares del postulante.
 *
 * Implementa la historia de usuario:
 *  - "Generación del Curriculum del Postulante (POST)"
 *
 * Este controlador es mantenido por:
 * @author Patricio Benavides
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/postulantes")
@Tag(
    name = "Curriculum Documento Controller",
    description = "Endpoints para generar y gestionar el CV del postulante."
)
public class CurriculumDocumentoController {

    private final CurriculumDocumentoService curriculumDocumentoService;

    @Operation(
        summary = "Generar Curriculum del Postulante",
        description = "Genera un archivo de CV en el formato indicado (pdf o word)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "CV generado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error de validación en los datos"),
        @ApiResponse(responseCode = "404", description = "No se encontró el postulante"),
        @ApiResponse(responseCode = "500", description = "Error interno al generar el CV")
    })
    @PostMapping("/{idPostulante}/cv")
    public ResponseEntity<byte[]> generarCv(
            @PathVariable Long idPostulante,

            @Parameter(
                description = "Formato en el que se generará el CV (pdf o word).",
                example = "pdf"
            )
            @RequestParam(defaultValue = "pdf") String formato) {

        try {
            // Por ahora sin validación de propietario (sin token / auth).
            CvFormato cvFormato = CvFormato.fromString(formato);

            byte[] archivo = curriculumDocumentoService.generarCv(idPostulante, cvFormato);

            String fileName = "cv_postulante_" + idPostulante + cvFormato.getExtension();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + fileName)
                    .contentType(MediaType.parseMediaType(cvFormato.getContentType()))
                    .body(archivo);

        } catch (DataValidationException e) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage().getBytes());

        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);

        } catch (ServiceException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
