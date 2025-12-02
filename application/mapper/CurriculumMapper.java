package es.altia.bne.postulante.application.mapper;


import java.util.Arrays;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.BneOcupacionesDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralArchivosDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemPresentacionDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneOcupaciones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboralArchivos;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemPresentacion;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemReferenciasLaborales;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CurriculumMapper {

    @Mapping(target = "idPostulante", source = "perPersonas.id")
    @Mapping(target = "textoCarta", source = "texto", qualifiedByName = "normalizeText")
    @Mapping(target = "fechaAlta", source = "fecAlta")
    @Mapping(target = "fechaModif", source = "fecModif")
    DemPresentacionDTO toPresentacionDto(DemPresentacion entity);

    @Mapping(target = "idPostulante", source = "perPersonas.id")
    @Mapping(target = "bneOcupaciones", source = "bneOcupaciones")
    @Mapping(target = "idRegion", source = "bneRegiones.id")
    @Mapping(target = "nombreRegion", source = "bneRegiones.nombre", qualifiedByName = "capitalize")
    @Mapping(target = "razonSocial", source = "razonSocial", qualifiedByName = "capitalize")
    @Mapping(target = "descripcion", source = "descripcion", qualifiedByName = "normalizeText")
    @Mapping(target = "referencias", source = "referencias", qualifiedByName = "normalizeText")
    @Mapping(target = "actualidad", expression = "java(entity.getFecFin() == null)")
    @Mapping(target = "demExpLaboralArchivos", source = "demExpLaboralArchivos")
    DemExpLaboralDTO toExpLaboralDto(DemExpLaboral entity);

    @Mapping(target = "idPostulante", source = "perPersonas.id")
    @Mapping(target = "nombre", source = "nombre", qualifiedByName = "capitalize")
    @Mapping(target = "puesto", source = "puesto", qualifiedByName = "capitalize")
    @Mapping(target = "empresa", source = "empresa", qualifiedByName = "capitalize")
    DemReferenciasLaboralesDTO toReferenciaDto(DemReferenciasLaborales entity);

    @Mapping(target = "idDemExpLaboral", source = "demExpLaboral.id")
    DemExpLaboralArchivosDTO toArchivoDto(DemExpLaboralArchivos entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "nombre", source = "nombre", qualifiedByName = "capitalize")
    BneOcupacionesDTO toOcupacionesDto(BneOcupaciones entity);

    @AfterMapping
    default void ensureRegionIds(@MappingTarget DemExpLaboralDTO target, DemExpLaboral source) {
        BneRegiones region = source.getBneRegiones();
        if (region == null) {
            target.setIdRegion(null);
            target.setNombreRegion(null);
        }
    }

    @Named("capitalize")
    default String capitalize(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(value.trim().toLowerCase().split("\\s+"))
                .filter(s -> !s.isBlank())
                .map(this::capitalizeWord)
                .collect(Collectors.joining(" "));
    }

    default String capitalizeWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    @Named("normalizeText")
    default String normalizeText(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s+", " ");
    }
}