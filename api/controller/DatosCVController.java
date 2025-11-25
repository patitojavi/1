package es.altia.bne.postulante.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/datosPostulante")
@Tag(name = "Datos Postulante Controller", description = "Controlador para gestionar acciones relacionadas con los datos personales de un postulante.")
public class DatosCVController {

//    @Autowired
//    private DatosPostulanteService datosService;
//
//    @Operation(summary = "Obtiene los datos personales de un postulante.", description = "Esto incluye: datos básicos, datos de contacto, direccion, sitio (cómo se enteró de este sitio) y datos de discapacidad")
//    @GetMapping("/datosPersonales/{idPostulante}")
//    public ResponseEntity<?> obtenerDatosPersonales(@PathVariable Long idPostulante) throws Exception {
//
//        return new ResponseEntity<>(datosService.obtenerDatosPersonales(idPostulante), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Obtiene los datos basicos de un postulante.")
//    @GetMapping("/datosBasicos/{idPostulante}")
//    public ResponseEntity<?> obtenerDatosBasicos(@PathVariable Long idPostulante) throws Exception {
//        return new ResponseEntity<>(datosService.obtenerDatosBasicos(idPostulante), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Obtiene los datos de contacto de un postulante.")
//    @GetMapping("/contacto/{idPostulante}")
//    public ResponseEntity<?> obtenerContacto(@PathVariable Long idPostulante) throws Exception {
//        return new ResponseEntity<>(datosService.obtenerContacto(idPostulante), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Actualiza los datos de contacto de un postulante.")
//    @PatchMapping("/actualizarContacto")
//    public ResponseEntity<?> actualizaContacto(@Valid @RequestBody DatosContactoDTO contactoDTO) throws Exception {
//        return new ResponseEntity<>(datosService.actualizarContacto(contactoDTO), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Obtiene la direccion de un postulante.")
//    @GetMapping("/direccion/{idPostulante}")
//    public ResponseEntity<?> obtenerDireccion(@PathVariable Long idPostulante) throws Exception {
//        return new ResponseEntity<>(datosService.obtenerDireccion(idPostulante), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Actualiza los datos de la direccion de un postulante.")
//    @PatchMapping("/actualizarDireccion")
//    public ResponseEntity<?> actualizarDireccion(@Valid @RequestBody DatosDireccionDTO direccionDTO) throws Exception {
//        return new ResponseEntity<>(datosService.actualizarDireccion(direccionDTO), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Actualiza los datos de discapacidad de un postulante.")
//    @PatchMapping("/actualizarDiscapacidad")
//    public ResponseEntity<?> actualizarDiscapacidad(@Valid @RequestBody DatosDiscapacidadDTO discapacidadDTO) throws Exception {
//        return new ResponseEntity<>(datosService.actualizarDiscapacidad(discapacidadDTO), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Actualiza los datos del sitio referente de un postulante. Persona solo trae id e idSitio")
//    @PatchMapping("/actualizarSitioReferente")
//    public ResponseEntity<?> actualizarSitioReferente(@Valid @RequestBody PerPersonaDTO persona) throws Exception {
//        return new ResponseEntity<>(datosService.actualizarSitioReferente(persona), HttpStatus.OK);
//    }
}
