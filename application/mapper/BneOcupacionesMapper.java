package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import es.altia.bne.postulante.application.dto.BneOcupacionesDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneOcupaciones;

@Mapper(componentModel = "spring")
public interface BneOcupacionesMapper {

    BneOcupacionesDTO toDto(BneOcupaciones entity);

    BneOcupaciones toEntity(BneOcupacionesDTO dto);

    void updateEntity(BneOcupacionesDTO dto, @MappingTarget BneOcupaciones entity);
}