package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneComunas;

@Mapper(componentModel = "spring")
public interface BneComunasMapper {

    @Named("comunaDesdeId")
    default BneComunas identificadorToEntidad(Integer idComuna) {
        if (idComuna == null) {
            return null;
        }
        BneComunas comuna = new BneComunas();
        comuna.setId(idComuna);
        return comuna;
    }
}
