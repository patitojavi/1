package es.altia.bne.postulante.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.service.CurriculumPostulanteService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST encargado de exponer los endpoints para
 * la obtención del Curriculum Vitae del postulante.
 *
 * Implementa la historia de usuario:
 *  - "Visualización del Curriculum del Postulante (GET)"
 *
 * Este controlador es mantenido por:
 * @author Patricio Benavides
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/postulantes")
@Tag(
    name = "Curriculum Postulante - Datos Curriculares",
    description = "Endpoints relacionados con la visualización y generación del Curriculum Vitae del postulante."
)
public class CurriculumPostulanteController {

    private final CurriculumPostulanteService curriculumPostulanteService;

    @Operation(
        summary = "Obtener Curriculum del Postulante",
        description = "Recupera toda la información disponible del Curriculum Vitae del postulante "
                    + "incluyendo datos personales, formación, experiencia, habilidades, y otros detalles relevantes."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curriculum obtenido correctamente"),
        @ApiResponse(responseCode = "404", description = "No se encontró información del postulante"),
        @ApiResponse(responseCode = "500", description = "Error interno al recuperar el Curriculum")
    })
    @GetMapping("/{idPostulante}/curriculum")
    public ResponseEntity<CurriculumVitaeDTO> obtenerCurriculum(@PathVariable Long idPostulante)
            throws Exception {

        CurriculumVitaeDTO curriculum = curriculumPostulanteService.obtenerCurriculum(idPostulante);
        return ResponseEntity.ok(curriculum);
    }
}
