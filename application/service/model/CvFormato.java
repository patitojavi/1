package es.altia.bne.postulante.application.service.model;

import java.util.Locale;

public enum CvFormato {
    PDF("application/pdf", ".pdf"),
    WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");

    private final String contentType;
    private final String extension;

    CvFormato(String contentType, String extension) {
        this.contentType = contentType;
        this.extension = extension;
    }

    public String getContentType() {
        return contentType;
    }

    public String getExtension() {
        return extension;
    }

    /**
     * Convierte un string a CvFormato.
     * Acepta valores como: "pdf", "word", "doc", "docx".
     * Si el valor es nulo o no coincide, se devuelve PDF por defecto.
     */
    public static CvFormato fromString(String value) {
        if (value == null) {
            return PDF;
        }

        String cleaned = value.trim().toLowerCase(Locale.ROOT);

        // Aceptamos varios alias para Word
        if (cleaned.equals("word") || cleaned.equals("doc") || cleaned.equals("docx")) {
            return WORD;
        }

        // Aceptamos explícitamente "pdf"
        if (cleaned.equals("pdf")) {
            return PDF;
        }

        // Por defecto, PDF
        return PDF;
    }
}
