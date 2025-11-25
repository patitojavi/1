package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneAreas;

@Mapper(componentModel = "spring")
public interface BneAreaMapper {

    @Named("areaId")
    default BneAreas identificadorToEntidad(Integer idArea) {
        if (idArea == null) {
            return null;
        }
        BneAreas area = new BneAreas();
        area.setId(idArea);
        return area;
    }
}
