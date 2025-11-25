package es.altia.bne.postulante.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.service.CurriculumPostulanteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postulantes")
public class CurriculumPostulanteController {

    private final CurriculumPostulanteService curriculumPostulanteService;

    @GetMapping("/{idPostulante}/curriculum")
    public ResponseEntity<CurriculumVitaeDTO> obtenerCurriculum(@PathVariable Long idPostulante)
            throws Exception {
        CurriculumVitaeDTO curriculum = curriculumPostulanteService.obtenerCurriculum(idPostulante);
        return ResponseEntity.ok(curriculum);
    }
}