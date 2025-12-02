package es.altia.bne.postulante.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.altia.bne.postulante.application.dto.DemConocimientosDTO;
import es.altia.bne.postulante.domain.model.DemConocimientos;

@Mapper(componentModel = "spring")
public interface DemConocimientosMapper {

    DemConocimientosDTO toDTO(DemConocimientos entity);

    List<DemConocimientosDTO> toDTOList(List<DemConocimientos> entities);

    DemConocimientos toEntity(DemConocimientosDTO dto);

    void updateEntity(DemConocimientosDTO dto, @MappingTarget DemConocimientos entity);
}