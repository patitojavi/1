package es.altia.bne.postulante.api.controller;

// ...existing code...
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import es.altia.bne.postulante.application.service.DatosPostulanteService;
import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/datosPostulante")
@Tag(name = "Datos Postulante Controller", description = "Controlador para gestionar acciones relacionadas con los datos personales de un postulante.")

public class DatosPostulanteController {
    private final DatosPostulanteService datosService;

    @Operation(summary = "Obtiene los datos personales de un postulante.", description = "Esto incluye: datos básicos, datos de contacto, direccion, sitio (cómo se enteró de este sitio) y datos de discapacidad")
    @GetMapping("/datosPersonales/{idPostulante}")
    public ResponseEntity<Object> obtenerDatosPersonales(@PathVariable Long idPostulante) throws DataValidationException, ResourceNotFoundException, ServiceException {
        return new ResponseEntity<>(datosService.obtenerDatosPersonales(idPostulante), HttpStatus.OK);
    }


    // Eliminado: endpoint /contacto/{idPostulante} (duplicado en DatosCVController). Mantener definición en DatosCVController (simulación).

    @Operation(summary = "Actualiza los datos de contacto de un postulante.")
    @PatchMapping("/actualizarContacto")
    public ResponseEntity<Object> actualizaContacto(@Valid @RequestBody DatosContactoDTO contactoDTO) throws ResourceNotFoundException, ServiceException {
        return new ResponseEntity<>(datosService.actualizarContacto(contactoDTO), HttpStatus.OK);
    }


    @Operation(summary = "Actualiza los datos de la direccion de un postulante.")
    @PatchMapping("/actualizarDireccion")
    public ResponseEntity<Object> actualizarDireccion(@Valid @RequestBody DatosDireccionDTO direccionDTO) throws ResourceNotFoundException, ServiceException {
        return new ResponseEntity<>(datosService.actualizarDireccion(direccionDTO), HttpStatus.OK);
    }



    @Operation(summary = "Actualiza los datos del sitio referente de un postulante. Persona solo trae id e idSitio")
    @PatchMapping("/actualizarSitioReferente")
    public ResponseEntity<Object> actualizarSitioReferente(@Valid @RequestBody PerPersonaDTO persona) throws ResourceNotFoundException, ServiceException {
        return new ResponseEntity<>(datosService.actualizarSitioReferente(persona), HttpStatus.OK);
    }
}
