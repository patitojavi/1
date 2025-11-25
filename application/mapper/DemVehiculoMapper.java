package es.altia.bne.postulante.application.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.DemVehiculosDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemVehiculos;

@Mapper(componentModel = "spring", uses = { BneVehiculoMapper.class })
public interface DemVehiculoMapper {

    @Mapping(target = "bneVehiculos.id", source = "idBneVehiculo")
    @Mapping(target = "perPersonas.id", source = "idPostulante")
    DemVehiculos toEntity(DemVehiculosDTO dto);

    @Mapping(source = "bneVehiculos.id", target = "idBneVehiculo")
    @Mapping(source = "bneVehiculos.nombre", target = "nombreBneVehiculo")
    @Mapping(source = "perPersonas.id", target = "idPostulante")
    DemVehiculosDTO toDTO(DemVehiculos entity);

    @Mapping(target = "perPersonas.id", source = "idPostulante")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DemVehiculosDTO dto, @MappingTarget DemVehiculos entity);
}
