package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la agregación de la información necesaria para construir el
 * curriculum vitae de un postulante.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumVitaeDTO implements Serializable {

    private static final long serialVersionUID = -6099303525807035257L;

    private DatosPersonalesDTO datosPersonales;
    private DemCondLabDTO condicionLaboral;
    private DemPresentacionDTO resumenPerfil;

    @Builder.Default
    private List<DemExpLaboralDTO> experienciasLaborales = Collections.emptyList();

    @Builder.Default
    private List<DemReferenciasLaboralesDTO> referenciasLaborales = Collections.emptyList();
    
    @Builder.Default
    private List<DemTitulacionesDTO> titulaciones = Collections.emptyList();
    
    @Builder.Default
    private List<DemVehiculosDTO> vehiculos = Collections.emptyList();
    
    @Builder.Default
    private List<DemCapacitacionesDTO> capacitaciones = Collections.emptyList();

    @Builder.Default
    private List<DemCertificacionesDTO> certificaciones = Collections.emptyList();

    @Builder.Default
    private List<DemHabilidadesDTO> habilidades = Collections.emptyList();

    @Builder.Default
    private List<DemIdiomasDTO> idiomas = Collections.emptyList();

    @Builder.Default
    private List<DemIdiomasTitulacionesDTO> idiomasTitulaciones = Collections.emptyList();

    @Builder.Default
    private List<DemServiciosDTO> servicios = Collections.emptyList();

    @Builder.Default
    private List<DemDisponibilidadDto> disponibilidades = Collections.emptyList();
}