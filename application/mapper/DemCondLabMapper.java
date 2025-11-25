package es.altia.bne.postulante.application.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.DemCondLabDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemCondLab;

@Mapper(componentModel = "spring", uses = { BneComunasMapper.class, BneRegionesMapper.class })
public interface DemCondLabMapper {

    /*
     * Anotación ¿Para qué sirve?
     * 
     * @Mapping(...) Define un mapeo explícito entre campos (source → target)
     * 
     * @Named("...") Da un nombre al método para reutilizarlo con qualifiedByName
     * 
     * @IterableMapping(...) Para mapear listas con métodos específicos
     * (qualifiedByName, etc.)
     * 
     * @BeanMapping(...) Permite ajustar detalles como
     * nullValuePropertyMappingStrategy
     * 
     * @ValueMapping(...) Para mapear enums con valores específicos
     * 
     * @InheritConfiguration Reutiliza la configuración de otro método de mapeo
     * 
     * @InheritInverseConfiguration Lo mismo pero al invertir source/target
     */

    @Mapping(target = "bneActEconomica.id", source = "idActEconomica")
    @Mapping(target = "bneSitLaboral.id", source = "idSitLaboral")
    @Mapping(target = "perPersonas.id", source = "idPostulante")
    DemCondLab toEntity(DemCondLabDTO dto);

    @Mapping(source = "bneActEconomica.id", target = "idActEconomica")
    @Mapping(source = "bneActEconomica.nombre", target = "nombreActEconomica")
    @Mapping(source = "bneSitLaboral.id", target = "idSitLaboral")
    @Mapping(source = "bneSitLaboral.nombre", target = "nombreSitLaboral")
    @Mapping(source = "perPersonas.id", target = "idPostulante")
    DemCondLabDTO toDTO(DemCondLab entity);
//
//    @Mapping(target = "bneActEconomica.id", source = "idActEconomica")
//    @Mapping(target = "bneSitLaboral.id", source = "idSitLaboral")
    @Mapping(target = "perPersonas.id", source = "idPostulante")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DemCondLabDTO dto, @MappingTarget DemCondLab entity);
}
