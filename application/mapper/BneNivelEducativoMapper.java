package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneNivelesEducativos;

@Mapper(componentModel = "spring")
public interface BneNivelEducativoMapper {

    @Named("nivelEducativoId")
    default BneNivelesEducativos identificadorToEntidad(Integer idNivelEducativo) {
        if (idNivelEducativo == null) {
            return null;
        }
        BneNivelesEducativos nivelesEducativo = new BneNivelesEducativos();
        nivelesEducativo.setId(idNivelEducativo);
        return nivelesEducativo;
    }
}
