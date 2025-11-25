package es.altia.bne.postulante.application.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePostulanteDTO {

    // === Estadísticas de postulaciones ===
    private Long totalPostulaciones;
    private Long postulacionesEnProceso;
    private Long postulacionesFinalizadas;
    private Long empresasQueVieronCv;

    // === Invitaciones SCT ===
    private List<InvitacionSCTDTO> invitacionesPendientes;

    // === Información personal / profesional ===
    private DatosPersonalesDTO datosPersonales;
    private DemCondLabDTO condicionLaboralActual;
    private DemTitulacionesDTO nivelEducacional;
    private DemExpLaboralDTO ultimaExperienciaLaboral;

    // === Estado del perfil ===
    private Boolean perfilCompleto;
    private Boolean consentimientoCV;

    // === Otros indicadores ===
    private Boolean recibeFondoCesantia;
    private Boolean feriasActivas;
    private Boolean encuestaPendiente;
}
