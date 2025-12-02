package es.altia.bne.postulante.api.controller;

import es.altia.bne.postulante.application.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * Controlador para gestionar todos los datos personales del postulante:
 * información básica, contacto, dirección, discapacidad y sitio referente.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/datosCV")
@Tag(name = "Datos CV (Simulación)", description = "Controlador simulado para ejemplos en Swagger. Usa base path /datosCV para evitar solapamiento con el controlador real.")
public class DatosCVController {

    // @Autowired
    // private DatosPostulanteService datosService;

    // 🧍‍♂️ === DATOS PERSONALES ===
    @Operation(
        summary = "Obtiene todos los datos personales de un postulante.",
        description = "Incluye datos básicos, contacto, dirección, discapacidad y sitio referente.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "datosBasicos": {"nombre": "Nelson", "apellido": "Quininao", "rut": "19.345.678-9"},
                  "contacto": {"telefono": "+56 9 2222 3333", "email": "nelson@correo.cl"},
                  "direccion": {"region": "Araucanía", "comuna": "Temuco", "calle": "Av. Alemania 123"},
                  "discapacidad": {"tipo": "Auditiva", "porcentaje": 30}
                }
                """))
            )
        }
    )
    @GetMapping("/datosPersonales/{idPostulante}")
    public ResponseEntity<?> obtenerDatosPersonales(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>("Simulación: datos personales", HttpStatus.OK);
    }

    // 📋 === DATOS BÁSICOS ===
    @Operation(
        summary = "Obtiene los datos básicos del postulante.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Datos básicos encontrados",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "nombre": "Nelson",
                  "apellidoPaterno": "Quininao",
                  "apellidoMaterno": "Neculhueque",
                  "rut": "19.345.678-9",
                  "fecNacimiento": "1998-05-12",
                  "sexo": "Masculino"
                }
                """))
            )
        }
    )
    @GetMapping("/datosBasicos/{idPostulante}")
    public ResponseEntity<?> obtenerDatosBasicos(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>("Simulación: datos básicos", HttpStatus.OK);
    }

    // 📞 === CONTACTO ===
    @Operation(
        summary = "Obtiene los datos de contacto del postulante.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Datos de contacto obtenidos",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "telefono": "+56 9 1234 5678",
                  "email": "nelson.quininao@bne.cl",
                  "telefonoAlternativo": "+56 45 224 1234"
                }
                """))
            )
        }
    )
    @GetMapping("/contacto/{idPostulante}")
    public ResponseEntity<?> obtenerContacto(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>("Simulación: contacto", HttpStatus.OK);
    }

    @Operation(
        summary = "Actualiza los datos de contacto del postulante.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = DatosContactoDTO.class),
                examples = @ExampleObject(value = """
                {
                  "idPostulante": 123,
                  "telefono": "+56 9 4444 5555",
                  "email": "nuevo@correo.cl"
                }
                """))
        )
    )
    @PatchMapping("/actualizarContacto")
    public ResponseEntity<?> actualizaContacto(@Valid @RequestBody DatosContactoDTO contactoDTO) throws Exception {
        return new ResponseEntity<>("Simulación: contacto actualizado", HttpStatus.OK);
    }

    // 🏠 === DIRECCIÓN ===
    @Operation(
        summary = "Obtiene la dirección registrada del postulante.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Dirección obtenida correctamente",
                content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                {
                  "region": "Araucanía",
                  "comuna": "Temuco",
                  "calle": "Av. Alemania 123",
                  "numero": "101"
                }
                """))
            )
        }
    )
    @GetMapping("/direccion/{idPostulante}")
    public ResponseEntity<?> obtenerDireccion(@PathVariable Long idPostulante) throws Exception {
        return new ResponseEntity<>("Simulación: dirección", HttpStatus.OK);
    }

    @Operation(
        summary = "Actualiza los datos de dirección del postulante.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = DatosDireccionDTO.class),
                examples = @ExampleObject(value = """
                {
                  "idPostulante": 123,
                  "region": "Araucanía",
                  "comuna": "Padre Las Casas",
                  "calle": "Colo Colo",
                  "numero": "500"
                }
                """))
        )
    )
    @PatchMapping("/actualizarDireccion")
    public ResponseEntity<?> actualizarDireccion(@Valid @RequestBody DatosDireccionDTO direccionDTO) throws Exception {
        return new ResponseEntity<>("Simulación: dirección actualizada", HttpStatus.OK);
    }

    // 🧠 === DISCAPACIDAD ===
    @Operation(
        summary = "Actualiza los datos de discapacidad del postulante.",
        description = "Permite registrar o modificar la información sobre discapacidad (tipo, porcentaje, certificados).",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DatosDiscapacidadDTO.class),
                examples = @ExampleObject(value = """
                {
                  "idPostulante": 123,
                  "tipo": "Visual",
                  "porcentaje": 45
                }
                """))
        )
    )
    @PatchMapping("/actualizarDiscapacidad")
    public ResponseEntity<?> actualizarDiscapacidad(@Valid @RequestBody DatosDiscapacidadDTO discapacidadDTO) throws Exception {
        return new ResponseEntity<>("Simulación: discapacidad actualizada", HttpStatus.OK);
    }

    // 🌐 === SITIO REFERENTE ===
    @Operation(
        summary = "Actualiza el sitio referente del postulante.",
        description = "Asocia el postulante a un sitio o fuente desde donde conoció la plataforma BNE.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PerPersonaDTO.class),
                examples = @ExampleObject(value = """
                {
                  "id": 123,
                  "idSitioReferente": 45
                }
                """))
        )
    )
    @PatchMapping("/actualizarSitioReferente")
    public ResponseEntity<?> actualizarSitioReferente(@Valid @RequestBody PerPersonaDTO persona) throws Exception {
        return new ResponseEntity<>("Simulación: sitio referente actualizado", HttpStatus.OK);
    }
}
