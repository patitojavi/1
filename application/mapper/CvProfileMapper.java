package es.altia.bne.postulante.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.altia.bne.postulante.application.dto.*;

@Mapper(componentModel = "spring")
public interface CvProfileMapper {

    CvProfileMapper INSTANCE = Mappers.getMapper(CvProfileMapper.class);

    /**
     * Combina los diferentes DTOs de entrada en un CvProfileDTO consolidado.
     *
     * @param datosBasicos   bloque con información personal del postulante
     * @param contacto       bloque con información de contacto
     * @param direccion      bloque con la dirección principal
     * @param experiencias   lista de experiencias laborales
     * @param educacion      lista de titulaciones y formación
     * @param habilidades    lista de habilidades (opcional)
     * @param idiomas        lista de idiomas (opcional)
     * @return un objeto CvProfileDTO completo
     */
    @Mapping(target = "idPostulante", source = "datosBasicos.id")
    @Mapping(target = "datosBasicos", source = "datosBasicos")
    @Mapping(target = "contacto", source = "contacto")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "experiencias", source = "experiencias")
    @Mapping(target = "educacion", source = "educacion")
    @Mapping(target = "habilidades", source = "habilidades")
    @Mapping(target = "idiomas", source = "idiomas")
    CvProfileDTO toDto(
        DatosBasicosDTO datosBasicos,
        DatosContactoDTO contacto,
        DatosDireccionDTO direccion,
        List<DemExpLaboralDTO> experiencias,
        List<DemTitulacionesDTO> educacion,
        List<DemHabilidadesDTO> habilidades,
        List<DemIdiomasDTO> idiomas
    );
}
