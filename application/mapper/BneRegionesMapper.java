package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;

@Mapper(componentModel = "spring")
public interface BneRegionesMapper {

    @Named("regionDesdeId")
    default BneRegiones identificadorToEntidad(Integer idRegion) {
        if (idRegion == null) {
            return null;
        }
        BneRegiones region = new BneRegiones();
        region.setId(idRegion);
        return region;
    }
}
