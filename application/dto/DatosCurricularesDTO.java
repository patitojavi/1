package es.altia.bne.postulante.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO que consolida todos los datos curriculares del postulante")
public class DatosCurricularesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID del postulante", example = "1")
    private Long idPostulante;
    
    // ========== Información básica ==========
    @Schema(description = "Datos personales del postulante")
    private DatosBasicosDTO datosBasicos;

    @Schema(description = "Datos de contacto del postulante")
    private DatosContactoDTO contacto;

    @Schema(description = "Dirección registrada del postulante")
    private DatosDireccionDTO direccion;
    
    // ========== Datos curriculares ==========
    @Schema(description = "Condición laboral actual")
    private DemCondLabDTO condicionLaboral;

    @Schema(description = "Resumen o presentación del perfil del postulante")
    private DemPresentacionDTO resumenPerfil;

    @Schema(description = "Lista de experiencias laborales del postulante")
    private transient List<DemExpLaboralDTO> experienciasLaborales;

    @Schema(description = "Lista de referencias laborales")
    private List<DemReferenciasLaboralesDTO> referenciasLaborales;

    @Schema(description = "Lista de capacitaciones realizadas")
    private List<DemCapacitacionesDTO> capacitaciones;

    @Schema(description = "Lista de idiomas que domina el postulante")
    private List<DemIdiomasDTO> idiomas;

    @Schema(description = "Lista de certificaciones profesionales")
    private List<DemCertificacionesDTO> certificaciones;

    @Schema(description = "Lista de experiencias educativas/formación")
    private List<DemExperienciaEducativaDTO> experienciasEducativas;
    
    // ========== Información complementaria ==========
    @Schema(description = "Información sobre discapacidad")
    private DatosDiscapacidadDTO discapacidad;

    @Schema(description = "Lista de habilidades del postulante")
    private List<DemHabilidadesDTO> habilidades;

    @Schema(description = "Lista de conocimientos técnicos")
    private transient List<DemConocimientosDTO> conocimientos;

    @Schema(description = "Lista de logros o reconocimientos")
    private List<DemLogrosDTO> logros;

    @Schema(description = "Fecha de última actualización", example = "2025-11-25T10:30:00")
    private LocalDateTime fechaActualizacion;
}
