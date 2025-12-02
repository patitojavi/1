package es.altia.bne.postulante.application.mapper;

import es.altia.bne.postulante.application.dto.*;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DatosCurricularesMapper {

    DatosCurricularesMapper INSTANCE = Mappers.getMapper(DatosCurricularesMapper.class);

    // Mapeos para DTO consolidados
    DatosCurricularesDTO toDatosCurricularesDto(DatosCurricularesDTO entity);
    
    DatosBasicosDTO toDatosBasicosDto(DatosBasicosDTO entity);
    DatosContactoDTO toDatosContactoDto(DatosContactoDTO entity);
    DatosDireccionDTO toDatosDireccionDto(DatosDireccionDTO entity);
    
    // Mapeos desde entidades - con ignore para propiedades no mapeables
    @Mapping(target = "idPostulante", ignore = true)
    @Mapping(target = "idActEconomica", ignore = true)
    @Mapping(target = "nombreActEconomica", ignore = true)
    @Mapping(target = "idSitLaboral", ignore = true)
    @Mapping(target = "nombreSitLaboral", ignore = true)
    DemCondLabDTO toDemCondLabDTO(DemCondLab entity);
    
    @Mapping(target = "idPostulante", ignore = true)
    @Mapping(target = "textoCarta", ignore = true)
    @Mapping(target = "fechaAlta", ignore = true)
    @Mapping(target = "fechaModif", ignore = true)
    DemPresentacionDTO toDemPresentacionDTO(DemPresentacion entity);
    
    @Mapping(target = "idPostulante", ignore = true)
    @Mapping(target = "idRegion", ignore = true)
    @Mapping(target = "nombreRegion", ignore = true)
    @Mapping(target = "modifica", ignore = true)
    @Mapping(target = "actualidad", ignore = true)
    @Mapping(target = "demExpLaboralArchivos", ignore = true)
    DemExpLaboralDTO toDemExpLaboralDTO(DemExpLaboral entity);
    
    @Mapping(target = "idPostulante", ignore = true)
    DemReferenciasLaboralesDTO toDemReferenciasLaboralesDTO(DemReferenciasLaborales entity);
    
    @Mapping(target = "perPersona", ignore = true)
    DemHabilidadesDTO toDemHabilidadesDTO(DemHabilidades entity);
    
    @Mapping(target = "idPostulante", ignore = true)
    @Mapping(target = "institucion", ignore = true)
    @Mapping(target = "titulo", ignore = true)
    @Mapping(target = "nivel", ignore = true)
    @Mapping(target = "fechaInicio", ignore = true)
    @Mapping(target = "fechaFin", ignore = true)
    @Mapping(target = "enCurso", ignore = true)
    @Mapping(target = "documentoRespaldo", ignore = true)
    DemExperienciaEducativaDTO toDemTitulacionesDTO(DemTitulaciones entity);
    
    DemCapacitacionesDTO toDemCapacitacionesDto(DemCapacitacionesDTO entity);
    DemIdiomasDTO toDemIdiomasDto(DemIdiomasDTO entity);
    DemCertificacionesDTO toDemCertificacionesDto(DemCertificacionesDTO entity);
    DemExperienciaEducativaDTO toDemExperienciaEducativaDto(DemExperienciaEducativaDTO entity);
    
    DatosDiscapacidadDTO toDatosDiscapacidadDto(DatosDiscapacidadDTO entity);
    DemConocimientosDTO toDemConocimientosDto(DemConocimientosDTO entity);
    DemLogrosDTO toDemLogrosDto(DemLogrosDTO entity);

}
