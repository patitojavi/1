package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneTipoEducacionMedia;

@Mapper(componentModel = "spring")
public interface BneTipoEducacionMediaMapper {

    @Named("tipoEducacionMediaId")
    default BneTipoEducacionMedia identificadorToEntidad(Integer idTipoEducacionMedia) {
        if (idTipoEducacionMedia == null) {
            return null;
        }
        BneTipoEducacionMedia tipoEducacionMedia = new BneTipoEducacionMedia();
        tipoEducacionMedia.setId(idTipoEducacionMedia);
        return tipoEducacionMedia;
    }
}
