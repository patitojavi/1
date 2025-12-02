package es.altia.bne.postulante.application.dto;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de intercambio para representar el CV consolidado que se serializa
 * en PDF o Word.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvDTO implements Serializable {

    private static final long serialVersionUID = -9001977408935132895L;

    private DatosBasicosDTO datosBasicos;
    private DatosContactoDTO contacto;
    private DatosDireccionDTO direccion;
    private DemCondLabDTO condicionLaboral;
    private DemPresentacionDTO resumenPerfil;

    @Builder.Default
    private List<DemExpLaboralDTO> experiencias = Collections.emptyList();

    @Builder.Default
    private List<DemTitulacionesDTO> formacion = Collections.emptyList();

    @Builder.Default
    private List<DemReferenciasLaboralesDTO> referencias = Collections.emptyList();

    @Builder.Default
    private List<DemCapacitacionesDTO> capacitaciones = Collections.emptyList();

    @Builder.Default
    private List<DemCertificacionesDTO> certificaciones = Collections.emptyList();

    @Builder.Default
    private List<DemHabilidadesDTO> habilidades = Collections.emptyList();

    @Builder.Default
    private List<DemIdiomasDTO> idiomas = Collections.emptyList();

    @Builder.Default
    private List<DemServiciosDTO> servicios = Collections.emptyList();

    @Builder.Default
    private List<DemDisponibilidadDto> disponibilidades = Collections.emptyList();
}