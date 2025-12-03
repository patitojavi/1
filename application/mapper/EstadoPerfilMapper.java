package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;

import es.altia.bne.postulante.application.dto.EstadoPerfilDTO;

@Mapper(componentModel = "spring")
public interface EstadoPerfilMapper {
    EstadoPerfilDTO toDTO(EstadoPerfilDTO entity);
    EstadoPerfilDTO toEntity(EstadoPerfilDTO dto);
}
