package es.altia.bne.postulante.application.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.dto.CvDTO;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.application.mapper.CvMapper;
import es.altia.bne.postulante.application.service.CurriculumDocumentoService;
import es.altia.bne.postulante.application.service.CurriculumPostulanteService;
import es.altia.bne.postulante.application.service.model.CvFormato;
import lombok.RequiredArgsConstructor;

// PDFBox
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

// Apache POI (Word)
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

@Service
@RequiredArgsConstructor
public class CurriculumDocumentoServiceImpl implements CurriculumDocumentoService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private final CurriculumPostulanteService curriculumPostulanteService;
    private final CvMapper cvMapper;

    @Override
    public byte[] generarCv(Long idPostulante, CvFormato formato)
            throws DataValidationException, ResourceNotFoundException, ServiceException {

        CurriculumVitaeDTO curriculum = curriculumPostulanteService.obtenerCurriculum(idPostulante);
        CvDTO cv = cvMapper.toCvDto(curriculum);

        // Validación de datos obligatorios del CV
        validarObligatorios(cv);

        return switch (formato) {
            case WORD -> generarWord(cv);
            case PDF -> generarPdf(cv);
        };
    }

    /**
     * Valida campos obligatorios del CV.
     */
    private void validarObligatorios(CvDTO curriculum) throws DataValidationException {
        DatosBasicosDTO basicos = curriculum.getDatosBasicos();
        if (basicos == null) {
            throw new DataValidationException("Faltan los datos personales del postulante");
        }

        if (estaVacio(basicos.getNombre())
                || estaVacio(basicos.getApe1())
                || estaVacio(basicos.getNumDocumento())) {
            throw new DataValidationException("Nombre, apellido y RUT son obligatorios para generar el CV");
        }
    }

    /**
     * Genera un PDF real usando PDFBox, con formato básico (título y subtítulos).
     */
    private byte[] generarPdf(CvDTO cv) throws ServiceException {
        String contenido = construirContenido(cv, "PDF");
        String[] lineas = contenido.split("\n");

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream =
                         new PDPageContentStream(document, page)) {

                float marginLeft = 50;
                float cursorY = 780;
                float leading = 16f; // espacio entre líneas

                // Título grande
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(marginLeft, cursorY);
                contentStream.showText("Curriculum Vitae");
                contentStream.endText();

                cursorY -= 30;

                // Saltamos la primera línea del contenido ("Curriculum Vitae (PDF)")
                for (int i = 1; i < lineas.length; i++) {
                    String linea = lineas[i];
                    String trimmed = linea.trim();

                    if (trimmed.isEmpty()) {
                        cursorY -= leading;
                        continue;
                    }

                    boolean esTitulo = trimmed.endsWith(":") && !trimmed.startsWith("-");

                    contentStream.beginText();
                    contentStream.newLineAtOffset(marginLeft, cursorY);

                    if (esTitulo) {
                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                    } else if (trimmed.startsWith("-")) {
                        contentStream.setFont(PDType1Font.HELVETICA, 11);
                    } else {
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                    }

                    contentStream.showText(trimmed);
                    contentStream.endText();

                    cursorY -= leading;

                    // (simplificado) si se acaba la página, dejamos de escribir
                    if (cursorY < 50) {
                        break;
                    }
                }
            }

            document.save(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new ServiceException("Error al generar el PDF del CV", e);
        }
    }

    /**
     * Genera un documento DOCX real usando Apache POI,
     * con título centrado y subtítulos en negrita.
     */
    private byte[] generarWord(CvDTO cv) throws ServiceException {
    String contenido = construirContenido(cv, "WORD");

    try (XWPFDocument document = new XWPFDocument();
         ByteArrayOutputStream out = new ByteArrayOutputStream()) {

        // Título principal centrado
        XWPFParagraph titulo = document.createParagraph();
        titulo.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun runTitulo = titulo.createRun();
        runTitulo.setText("Curriculum Vitae");
        runTitulo.setBold(true);
        runTitulo.setFontSize(22);
        runTitulo.addBreak();

        // Párrafos del contenido
        String[] lineas = contenido.split("\n");
        // saltamos la primera línea: "Curriculum Vitae (WORD)"
        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i].trim();
            if (linea.isEmpty()) {
                continue;
            }

            XWPFParagraph p = document.createParagraph();
            p.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun run = p.createRun();
            run.setFontFamily("Calibri");
            run.setFontSize(12);

            boolean esTitulo = linea.endsWith(":") && !linea.startsWith("-");

            if (esTitulo) {
                run.setBold(true);
                run.setFontSize(14);
            }

            run.setText(linea);
        }

        document.write(out);
        return out.toByteArray();

    } catch (IOException e) {
        // Aquí es donde se está lanzando el 500
        throw new ServiceException("Error al generar el documento Word del CV", e);
    }
}


    private String construirContenido(CvDTO curriculum, String etiquetaFormato) {
        StringBuilder builder = new StringBuilder();
        builder.append("Curriculum Vitae (").append(etiquetaFormato).append(")\n");

        DatosBasicosDTO basicos = curriculum.getDatosBasicos();
        builder.append("Nombre completo: ").append(nombreCompleto(basicos)).append('\n');
        builder.append("RUT: ").append(formatearRut(basicos)).append('\n');

        Optional.ofNullable(curriculum.getContacto())
                .ifPresent(contacto -> builder.append("Email: ")
                        .append(Optional.ofNullable(contacto.getEmail()).orElse("-"))
                        .append(" | Teléfono: ")
                        .append(Optional.ofNullable(contacto.getTelefonoNotificaciones()).orElse("-"))
                        .append('\n'));

        DemCondLabDTO condicionLaboral = curriculum.getCondicionLaboral();
        if (condicionLaboral != null) {
            builder.append("Situación laboral: ")
                    .append(Optional.ofNullable(condicionLaboral.getNombreSitLaboral()).orElse("-"))
                    .append(" desde ")
                    .append(formatearFecha(condicionLaboral.getFecIni()))
                    .append('\n');
        }

        Optional.ofNullable(curriculum.getResumenPerfil())
                .map(DemPresentacionDTO::getTextoCarta)
                .filter(texto -> !texto.isBlank())
                .ifPresent(texto -> builder.append("\nPerfil profesional:\n")
                        .append(texto)
                        .append("\n"));

        // Experiencia laboral
        builder.append("\nExperiencia laboral:\n");
        if (curriculum.getExperiencias() == null || curriculum.getExperiencias().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemExpLaboralDTO experiencia : curriculum.getExperiencias()) {
                builder.append("- ")
                        .append(Optional.ofNullable(experiencia.getRazonSocial())
                                .orElse("Empresa no informada"))
                        .append(" (")
                        .append(formatearFecha(experiencia.getFecIni()))
                        .append(" - ")
                        .append(Boolean.TRUE.equals(experiencia.getActualidad())
                                ? "Actual"
                                : formatearFecha(experiencia.getFecFin()))
                        .append(")\n");

                if (experiencia.getDescripcion() != null) {
                    builder.append("  ")
                            .append(experiencia.getDescripcion())
                            .append('\n');
                }
            }
        }

        // Referencias laborales
        builder.append("\nReferencias laborales:\n");
        if (curriculum.getReferencias() == null || curriculum.getReferencias().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemReferenciasLaboralesDTO referencia : curriculum.getReferencias()) {
                builder.append("- ")
                        .append(Optional.ofNullable(referencia.getNombre())
                                .orElse("Nombre no informado"))
                        .append(" | ")
                        .append(Optional.ofNullable(referencia.getEmpresa())
                                .orElse("Empresa no informada"))
                        .append(" | Tel: ")
                        .append(Optional.ofNullable(referencia.getTelefono())
                                .orElse("-"))
                        .append('\n');
            }
        }

        // Formación
        builder.append("\nFormación:\n");
        if (curriculum.getFormacion() == null || curriculum.getFormacion().isEmpty()) {
            builder.append("- Sin registros\n");
        } else {
            for (DemTitulacionesDTO titulacion : curriculum.getFormacion()) {
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
        if (basicos == null) {
            return "-";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(capitalize(basicos.getNombre()));
        builder.append(' ').append(capitalize(basicos.getApe1()));
        if (!estaVacio(basicos.getApe2())) {
            builder.append(' ').append(capitalize(basicos.getApe2()));
        }
        return builder.toString().trim();
    }

    private String formatearRut(DatosBasicosDTO basicos) {
        if (basicos == null || estaVacio(basicos.getNumDocumento())) {
            return "-";
        }
        String dv = basicos.getDigitoVerificador();
        return (dv != null && !dv.isBlank())
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
