package es.altia.bne.postulante.api.controller;

import es.altia.bne.postulante.application.dto.DireccionDTO;
import es.altia.bne.postulante.application.service.DireccionPostulanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postulantes")
public class PostulanteDireccionController {

    private final DireccionPostulanteService direccionPostulanteService;

    @PutMapping("/{idPostulante}/direccion")
    public ResponseEntity<DireccionDTO> actualizarDireccion(
            @PathVariable("idPostulante") Long idPostulante,
            @Valid @RequestBody DireccionDTO direccionDTO) {

        DireccionDTO actualizada = direccionPostulanteService.actualizarDireccion(idPostulante, direccionDTO);
        return ResponseEntity.ok(actualizada);
    }
}