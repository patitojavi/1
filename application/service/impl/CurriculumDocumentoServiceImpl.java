package es.altia.bne.postulante.application.service.impl;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosPersonalesDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.service.CurriculumDocumentoService;
import es.altia.bne.postulante.application.service.CurriculumPostulanteService;
import es.altia.bne.postulante.application.service.model.CvFormato;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumDocumentoServiceImpl implements CurriculumDocumentoService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final CurriculumPostulanteService curriculumPostulanteService;

    @Override
    public byte[] generarCv(Long idPostulante, CvFormato formato)
            throws DataValidationException, ResourceNotFoundException, ServiceException {
        CurriculumVitaeDTO curriculum = curriculumPostulanteService.obtenerCurriculum(idPostulante);
        validarObligatorios(curriculum);
        normalizarCurriculum(curriculum);

        String contenido = construirContenido(curriculum, formato);
        return contenido.getBytes(StandardCharsets.UTF_8);
    }

    private void validarObligatorios(CurriculumVitaeDTO curriculum) throws DataValidationException {
        DatosPersonalesDTO datosPersonales = curriculum.getDatosPersonales();
        DatosBasicosDTO basicos = datosPersonales != null ? datosPersonales.getDatosBasicos() : null;
        if (basicos == null) {
            throw new DataValidationException("Faltan los datos personales del postulante");
        }

        if (estaVacio(basicos.getNombre()) || estaVacio(basicos.getApe1()) || estaVacio(basicos.getNumDocumento())) {
            throw new DataValidationException("Nombre, apellido y RUT son obligatorios para generar el CV");
        }
    }

    private void normalizarCurriculum(CurriculumVitaeDTO curriculum) {
        Optional.ofNullable(curriculum.getDatosPersonales())
                .map(DatosPersonalesDTO::getDatosBasicos)
                .ifPresent(basicos -> {
                    basicos.setNombre(capitalize(basicos.getNombre()));
                    basicos.setApe1(capitalize(basicos.getApe1()));
                    basicos.setApe2(capitalize(basicos.getApe2()));
                });

        Optional.ofNullable(curriculum.getResumenPerfil())
                .ifPresent(resumen -> resumen.setTextoCarta(normalize(resumen.getTextoCarta())));

        curriculum.getExperienciasLaborales().forEach(exp -> {
            exp.setRazonSocial(capitalize(exp.getRazonSocial()));
            exp.setDescripcion(normalize(exp.getDescripcion()));
        });

        curriculum.getReferenciasLaborales().forEach(ref -> {
            ref.setNombre(capitalize(ref.getNombre()));
            ref.setEmpresa(capitalize(ref.getEmpresa()));
            ref.setPuesto(capitalize(ref.getPuesto()));
        });
    }

    private String construirContenido(CurriculumVitaeDTO curriculum, CvFormato formato) {
        StringBuilder builder = new StringBuilder();
        builder.append("Curriculum Vitae (" + formato.name() + ")\n");

        DatosBasicosDTO basicos = curriculum.getDatosPersonales().getDatosBasicos();
        builder.append("Nombre completo: ").append(nombreCompleto(basicos)).append('\n');
        builder.append("RUT: ").append(formatearRut(basicos)).append('\n');

        Optional.ofNullable(curriculum.getDatosPersonales().getDatosContacto())
                .ifPresent(contacto -> builder.append("Email: ")
                        .append(Optional.ofNullable(contacto.getEmail()).orElse("-"))
                        .append(" | Teléfono: ")
                        .append(Optional.ofNullable(contacto.getTelefonoNotificaciones()).orElse("-"))
                        .append('\n'));

        DemCondLabDTO condicionLaboral = curriculum.getCondicionLaboral();
        if (condicionLaboral != null) {
            builder.append("Situación laboral: ")
                    .append(Optional.ofNullable(condicionLaboral.getNombreSitLaboral()).orElse("-"))
                    .append(" desde ").append(formatearFecha(condicionLaboral.getFecIni()))
                    .append('\n');
        }

        Optional.ofNullable(curriculum.getResumenPerfil())
                .map(DemPresentacionDTO::getTextoCarta)
                .filter(texto -> !texto.isBlank())
                .ifPresent(texto -> builder.append("\nPerfil profesional:\n").append(texto).append("\n"));

        builder.append("\nExperiencia laboral:\n");
        if (curriculum.getExperienciasLaborales().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemExpLaboralDTO experiencia : curriculum.getExperienciasLaborales()) {
                builder.append("- ")
                        .append(Optional.ofNullable(experiencia.getRazonSocial()).orElse("Empresa no informada"))
                        .append(" (")
                        .append(formatearFecha(experiencia.getFecIni()))
                        .append(" - ")
                        .append(experiencia.getActualidad() ? "Actual" : formatearFecha(experiencia.getFecFin()))
                        .append(")\n");
                if (experiencia.getDescripcion() != null) {
                    builder.append("  ").append(experiencia.getDescripcion()).append('\n');
                }
            }
        }

        builder.append("\nReferencias laborales:\n");
        if (curriculum.getReferenciasLaborales().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemReferenciasLaboralesDTO referencia : curriculum.getReferenciasLaborales()) {
                builder.append("- ")
                        .append(Optional.ofNullable(referencia.getNombre()).orElse("Nombre no informado"))
                        .append(" | ")
                        .append(Optional.ofNullable(referencia.getEmpresa()).orElse("Empresa no informada"))
                        .append(" | Tel: ")
                        .append(Optional.ofNullable(referencia.getTelefono()).orElse("-"))
                        .append('\n');
            }
        }

        // 🔧 SECCIÓN CORREGIDA: Formación
        builder.append("\nFormación:\n");
        if (curriculum.getTitulaciones().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemTitulacionesDTO titulacion : curriculum.getTitulaciones()) {
                builder.append("- ")
                        .append(Optional.ofNullable(titulacion.getNombreBneTitulacionesUniv())
                                .orElse("Titulación no informada"))
                        .append(" | Institución: ")
                        .append(Optional.ofNullable(titulacion.getNombreInst())
                                .orElse("-"))
                        .append('\n');
            }
        }

        return builder.toString();
    }

    private String nombreCompleto(DatosBasicosDTO basicos) {
        StringBuilder builder = new StringBuilder();
        builder.append(capitalize(basicos.getNombre()));
        builder.append(' ').append(capitalize(basicos.getApe1()));
        if (!estaVacio(basicos.getApe2())) {
            builder.append(' ').append(capitalize(basicos.getApe2()));
        }
        return builder.toString().trim();
    }

    private String formatearRut(DatosBasicosDTO basicos) {
        if (estaVacio(basicos.getNumDocumento())) {
            return "-";
        }
        String dv = basicos.getDigitoVerificador();
        return dv != null && !dv.isBlank()
                ? basicos.getNumDocumento() + "-" + dv
                : basicos.getNumDocumento();
    }

    private String formatearFecha(Date fecha) {
        if (fecha == null) {
            return "Sin fecha";
        }
        return DATE_FORMAT.format(fecha);
    }

    private boolean estaVacio(String value) {
        return value == null || value.isBlank();
    }

    private String normalize(String value) {
        return value == null ? null : value.trim().replaceAll("\\s+", " ");
    }

    private String capitalize(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return trimmed;
        }
        String lower = trimmed.toLowerCase();
        String[] parts = lower.split("\\s+");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            builder.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(' ');
        }
        return builder.toString().trim();
    }
}
