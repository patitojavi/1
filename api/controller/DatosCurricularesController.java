package es.altia.bne.postulante.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.service.DatosCurricularesService;
import es.altia.bne.postulante.application.service.DemCondLabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/datosCurriculares")
@Tag(name = "Datos Postulante Controller", description = "Controlador para gestionar acciones relacionadas con los datos curriculares de un postulante.")
public class DatosCurricularesController {

    @Autowired
    private DatosCurricularesService datosService;
    @Autowired
    private DemCondLabService demCondLabService;

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
}
