package es.altia.bne.postulante.application.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemTitulaciones;

@Mapper(componentModel = "spring", uses = { BneVehiculoMapper.class })
public interface DemTitulacionMapper {

    @Mapping(target = "bneAreas.id", source = "idBneAreas")
    @Mapping(target = "bneNivelesEducativos.id", source = "idBneNivelesEducativos")
    @Mapping(target = "bneRegiones.id", source = "idBneRegiones")
    @Mapping(target = "bneTipoEducacionMedia.id", source = "idBneTipoEducacionMedia")
    @Mapping(target = "bneTitulacionesUniv.id", source = "idBneTitulacionesUniv")
    @Mapping(target = "perPersonas.id", source = "idPostulante")
    DemTitulaciones toEntity(DemTitulacionesDTO dto);

    @Mapping(source = "bneAreas.id", target = "idBneAreas")
    @Mapping(source = "bneAreas.nombre", target = "nombreBneAreas")
    @Mapping(source = "bneNivelesEducativos.id", target = "idBneNivelesEducativos")
    @Mapping(source = "bneNivelesEducativos.nombre", target = "nombreBneNivelesEducativos")
    @Mapping(source = "bneRegiones.id", target = "idBneRegiones")
    @Mapping(source = "bneRegiones.nombre", target = "nombreBneRegiones")
    @Mapping(source = "bneTipoEducacionMedia.id", target = "idBneTipoEducacionMedia")
    @Mapping(source = "bneTipoEducacionMedia.nombre", target = "nombreBneTipoEducacionMedia")
    @Mapping(source = "bneTitulacionesUniv.id", target = "idBneTitulacionesUniv")
    @Mapping(source = "bneTitulacionesUniv.nombre", target = "nombreBneTitulacionesUniv")
    @Mapping(source = "perPersonas.id", target = "idPostulante")
    DemTitulacionesDTO toDTO(DemTitulaciones entity);

    @Mapping(target = "perPersonas.id", source = "idPostulante")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DemTitulacionesDTO dto, @MappingTarget DemTitulaciones entity);
}
