package es.altia.bne.postulante.application.mapper;

import java.util.List;

import org.mapstruct.*;

import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.domain.model.DemExpLaboral;

@Mapper(componentModel = "spring")
public interface DemExpLaboralMapper {

    @Mapping(target = "idOcupacion", source = "bneOcupaciones.id")
    @Mapping(target = "fecModif", expression = "java(new java.util.Date())")
    @Mapping(target = "fecAlta", expression = "java(new java.util.Date())")
    DemExpLaboral toEntity(DemExpLaboralDTO dto);

    @Mapping(target = "bneOcupaciones", ignore = true)
    @Mapping(target = "demExpLaboralArchivos", ignore = true)
    @Mapping(target = "modifica", ignore = true)
    @Mapping(target = "actualidad", ignore = true)
    DemExpLaboralDTO toDTO(DemExpLaboral entity);

    List<DemExpLaboralDTO> toDTOList(List<DemExpLaboral> entities);

    @Mapping(target = "idOcupacion", source = "bneOcupaciones.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecAlta", ignore = true)
    @Mapping(target = "fecModif", expression = "java(new java.util.Date())")
    void updateEntity(DemExpLaboralDTO dto, @MappingTarget DemExpLaboral entity);

}