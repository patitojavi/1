package es.altia.bne.postulante.application.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DatosPersonalesDTO implements Serializable {

    private static final long serialVersionUID = 4211167055602364716L;

    private DatosBasicosDTO datosBasicos;

    private DatosContactoDTO datosContacto;

    private DatosDireccionDTO direccion;

    private DatosDiscapacidadDTO discapacidad;

//    private Integer idNivelEducacionalMaximo;

    @NotNull(message = "{postulantes.datosPersonales.otros.sitioReferencia.notempty}")
    private Integer idSitioReferencia;

    public DatosPersonalesDTO(DatosBasicosDTO datosBasicos, DatosContactoDTO datosContacto, DatosDireccionDTO direccion,
            DatosDiscapacidadDTO discapacidad, Integer idSitioReferencia) {
        this.datosBasicos = datosBasicos;
        this.datosContacto = datosContacto;
        this.direccion = direccion;
        this.discapacidad = discapacidad;
//        this.idNivelEducacionalMaximo = idNivelEducacionalMaximo;
        this.idSitioReferencia = idSitioReferencia;
    }
}
