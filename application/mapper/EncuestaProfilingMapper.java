package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;

import es.altia.bne.postulante.application.dto.EncuestaProfilingDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.EncuestaProfiling;

@Mapper(componentModel = "spring")
public interface EncuestaProfilingMapper {
    EncuestaProfilingDTO toDTO(EncuestaProfiling entity);
    EncuestaProfiling toEntity(EncuestaProfilingDTO dto);
}
