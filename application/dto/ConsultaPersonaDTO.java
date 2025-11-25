package es.altia.bne.postulante.application.dto;

import lombok.Data;

/**
 * DTO para respuesta de consulta de persona por documento.
 * Incluye información básica de la persona.
 *
 * @author gaspar.jimenez
 */
@Data
public class ConsultaPersonaDTO {

    private boolean existe;
    private Long id;
    private String numDocumento;
    private String digitoVerificador;
    private Integer tipoDocumento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String estado;
    private String mensaje;

    public ConsultaPersonaDTO() {
    }

    public ConsultaPersonaDTO(boolean existe, String mensaje) {
        this.existe = existe;
        this.mensaje = mensaje;
    }

    public ConsultaPersonaDTO(Long id, String numDocumento, String digitoVerificador, 
                             Integer tipoDocumento, String nombre, String apellidoPaterno, 
                             String apellidoMaterno, String email, String estado) {
        this.existe = true;
        this.id = id;
        this.numDocumento = numDocumento;
        this.digitoVerificador = digitoVerificador;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.estado = estado;
        this.mensaje = "Persona encontrada correctamente";
    }
}