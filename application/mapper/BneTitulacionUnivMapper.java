package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneTitulacionesUniv;

@Mapper(componentModel = "spring")
public interface BneTitulacionUnivMapper {

    @Named("titulacionUnivId")
    default BneTitulacionesUniv identificadorToEntidad(Integer idTitulacionUniv) {
        if (idTitulacionUniv == null) {
            return null;
        }
        BneTitulacionesUniv titulacionesUniv = new BneTitulacionesUniv();
        titulacionesUniv.setId(idTitulacionUniv);
        return titulacionesUniv;
    }
}
