package es.altia.bne.postulante.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.altia.bne.postulante.application.dto.ConsultaPersonaDTO;
import es.altia.bne.postulante.application.service.ConsultaPersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/postulante")
@Tag(name = "Consulta Postulante Controller", description = "Controlador para consultar información de postulantes por RUT")
@Log4j2
public class ConsultaPostulanteController {

    @Autowired
    private ConsultaPersonaService consultaPersonaService;

    @Operation(summary = "Consultar persona por documento", description = "Consulta si existe una persona con el documento especificado. "
            + "La búsqueda se realiza por tipo de documento y número de documento. "
            + "El número de documento puede contener letras, números y caracteres especiales. "
            + "El dígito verificador es opcional y no se calcula automáticamente.")
    @GetMapping
    public ResponseEntity<ConsultaPersonaDTO> consultarPersonaPorRut(@Parameter(description = "Tipo de documento (1 = RUT, 2 = Otro, etc.)", required = true, example = "1") @RequestParam("tipoDoc") Integer tipoDocumento,

                                                                     @Parameter(description = "Número de documento (puede contener letras, números y caracteres especiales)", required = true, example = "12345678") @RequestParam("numero") String numero,

                                                                     @Parameter(description = "Dígito verificador del documento (opcional)", required = false, example = "9") @RequestParam(value = "dv", required = false) String digitoVerificador) {

        try {
            log.info("Solicitud de consulta de persona - tipoDoc: {}, numero: {}, dv: {}", tipoDocumento, numero, digitoVerificador);

            ConsultaPersonaDTO resultado = consultaPersonaService.consultarPersonaPorRut(tipoDocumento, numero, digitoVerificador);

            // Determinar el status HTTP basado en el resultado
            HttpStatus status = resultado.isExiste() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

            return new ResponseEntity<>(resultado, status);
        } catch (Exception e) {
            log.error("Error en consulta de persona por documento: {}", e.getMessage(), e);

            ConsultaPersonaDTO errorResponse = new ConsultaPersonaDTO(false, "Error interno del servidor: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
