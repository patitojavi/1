package es.altia.bne.postulante.application.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DemConsentirInfoCVDTO implements java.io.Serializable {

    private static final long serialVersionUID = 656132040569318076L;
    private Long id;
    private PerPersonaDTO perPersona;
    private Boolean consentirGruposEmpleo;
    private Boolean consentirIdiomas;
    private Boolean consentirLicenciasConducir;
    private Boolean consentirVehiculos;
    private Date fecAlta;
    private Date fecModif;
    private Boolean mostrarCvEmpresas;

    public DemConsentirInfoCVDTO() {
    }

    public DemConsentirInfoCVDTO(Long id, PerPersonaDTO perPersona, Boolean consentirGruposEmpleo,
            Boolean consentirIdiomas, Boolean consentirLicenciasConducir, Boolean consentirVehiculos, Date fecAlta,
            Date fecModif, Boolean mostrarCvEmpresas) {
        super();
        this.id = id;
        this.perPersona = perPersona;
        this.consentirGruposEmpleo = consentirGruposEmpleo;
        this.consentirIdiomas = consentirIdiomas;
        this.consentirLicenciasConducir = consentirLicenciasConducir;
        this.consentirVehiculos = consentirVehiculos;
        this.fecAlta = fecAlta;
        this.fecModif = fecModif;
        this.mostrarCvEmpresas = mostrarCvEmpresas;
    }

}
