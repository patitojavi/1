package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DatosBasicosDTO implements Serializable  {

    private static final long serialVersionUID = 6266628286075859956L;
    @NotNull(message = "El id no puede estar vacío")
    @Positive(message = "El id debe ser mayor que cero")
    private Long id;
    private String numDocumento;
    private String nombre;
    private String ape1;
    private String ape2;
    private Date fecNac;
    private String sexo;
    private Integer idNacionalidad;
    private Integer idEstadoCivil;
   
    private String digitoVerificador;
    private Integer tipoDocumento;
    private Boolean indMostrarDatospostulante; // nuevo

    public DatosBasicosDTO() {
    }

    public DatosBasicosDTO(
            Long id,
            String numDocumento, String nombre, String ape1, String ape2, Date fecNac, String sexo,
            Integer idNacionalidad, Integer idEstadoCivil,
            String digitoVerificador, Integer tipoDocumento, Boolean indMostrarDatospostulante) {
        super();
        this.id = id;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.fecNac = fecNac;
        this.sexo = sexo;
        this.idNacionalidad = idNacionalidad;
        this.idEstadoCivil = idEstadoCivil;
      
        this.digitoVerificador = digitoVerificador;
        this.tipoDocumento = tipoDocumento;
        this.indMostrarDatospostulante = indMostrarDatospostulante;
    }
}