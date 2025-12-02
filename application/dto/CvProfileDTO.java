package es.altia.bne.postulante.application.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class CvProfileDTO implements Serializable {

    private static final long serialVersionUID = 9143872637812349876L;

    private Long idPostulante;
    private DatosBasicosDTO datosBasicos;
    private DatosContactoDTO contacto;
    private DatosDireccionDTO direccion;
    private List<DemExpLaboralDTO> experiencias;
    private List<DemTitulacionesDTO> educacion;
    private List<DemHabilidadesDTO> habilidades;
    private List<DemIdiomasDTO> idiomas;

    public CvProfileDTO() {
    }

    public CvProfileDTO(
            Long idPostulante,
            DatosBasicosDTO datosBasicos,
            DatosContactoDTO contacto,
            DatosDireccionDTO direccion,
            List<DemExpLaboralDTO> experiencias,
            List<DemTitulacionesDTO> educacion,
            List<DemHabilidadesDTO> habilidades,
            List<DemIdiomasDTO> idiomas) {
        this.idPostulante = idPostulante;
        this.datosBasicos = datosBasicos;
        this.contacto = contacto;
        this.direccion = direccion;
        this.experiencias = experiencias;
        this.educacion = educacion;
        this.habilidades = habilidades;
        this.idiomas = idiomas;
    }
}
