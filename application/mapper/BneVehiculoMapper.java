package es.altia.bne.postulante.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneVehiculos;

@Mapper(componentModel = "spring")
public interface BneVehiculoMapper {

    @Named("vehiculoId")
    default BneVehiculos identificadorToEntidad(Integer idVehiculo) {
        if (idVehiculo == null) {
            return null;
        }
        BneVehiculos vehiculo = new BneVehiculos();
        vehiculo.setId(idVehiculo);
        return vehiculo;
    }
}
