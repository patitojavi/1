package es.altia.bne.postulante.application.mapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.CurriculumVitaeDTO;
import es.altia.bne.postulante.application.dto.CvDTO;
import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DemExpLaboralDTO;
import es.altia.bne.postulante.application.dto.DemReferenciasLaboralesDTO;
import es.altia.bne.postulante.application.dto.DemTitulacionesDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CvMapper {

    @Mapping(target = "datosBasicos", source = "datosPersonales.datosBasicos", qualifiedByName = "normalizeBasicos")
    @Mapping(target = "contacto", source = "datosPersonales.datosContacto")
    @Mapping(target = "direccion", source = "datosPersonales.direccion")
    @Mapping(target = "resumenPerfil", source = "resumenPerfil")
    @Mapping(target = "experiencias", source = "experienciasLaborales")
    @Mapping(target = "formacion", source = "titulaciones")
    @Mapping(target = "referencias", source = "referenciasLaborales")
    @Mapping(target = "capacitaciones", source = "capacitaciones")
    @Mapping(target = "certificaciones", source = "certificaciones")
    @Mapping(target = "habilidades", source = "habilidades")
    @Mapping(target = "idiomas", source = "idiomas")
    @Mapping(target = "servicios", source = "servicios")
    @Mapping(target = "disponibilidades", source = "disponibilidades")
    CvDTO toCvDto(CurriculumVitaeDTO source);

    @AfterMapping
    default void ensureLists(@MappingTarget CvDTO target) {
        if (target.getExperiencias() == null) {
            target.setExperiencias(Collections.emptyList());
        }
        if (target.getFormacion() == null) {
            target.setFormacion(Collections.emptyList());
        }
        if (target.getReferencias() == null) {
            target.setReferencias(Collections.emptyList());
        }
        if (target.getCapacitaciones() == null) {
            target.setCapacitaciones(Collections.emptyList());
        }
        if (target.getCertificaciones() == null) {
            target.setCertificaciones(Collections.emptyList());
        }
        if (target.getHabilidades() == null) {
            target.setHabilidades(Collections.emptyList());
        }
        if (target.getIdiomas() == null) {
            target.setIdiomas(Collections.emptyList());
        }
        if (target.getServicios() == null) {
            target.setServicios(Collections.emptyList());
        }
        if (target.getDisponibilidades() == null) {
            target.setDisponibilidades(Collections.emptyList());
        }

        Optional.ofNullable(target.getExperiencias())
                .ifPresent(list -> list.forEach(this::normalizeExperience));

        Optional.ofNullable(target.getReferencias())
                .ifPresent(list -> list.forEach(this::normalizeReference));

        Optional.ofNullable(target.getFormacion())
                .ifPresent(list -> list.forEach(this::normalizeDegree));
    }

    @Named("normalizeBasicos")
    default DatosBasicosDTO normalizeBasicos(DatosBasicosDTO basicos) {
        if (basicos == null) {
            return null;
        }
        basicos.setNombre(capitalize(basicos.getNombre()));
        basicos.setApe1(capitalize(basicos.getApe1()));
        basicos.setApe2(capitalize(basicos.getApe2()));
        return basicos;
    }

    default void normalizeExperience(DemExpLaboralDTO exp) {
        exp.setRazonSocial(capitalize(exp.getRazonSocial()));
        exp.setDescripcion(normalizeText(exp.getDescripcion()));
        exp.setReferencias(normalizeText(exp.getReferencias()));
    }

    default void normalizeReference(DemReferenciasLaboralesDTO ref) {
        ref.setNombre(capitalize(ref.getNombre()));
        ref.setEmpresa(capitalize(ref.getEmpresa()));
        ref.setPuesto(capitalize(ref.getPuesto()));
    }

    default void normalizeDegree(DemTitulacionesDTO titulacion) {
        titulacion.setNombreInst(capitalize(titulacion.getNombreInst()));
        titulacion.setNombreBneTitulacionesUniv(capitalize(titulacion.getNombreBneTitulacionesUniv()));
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

    default String normalizeText(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s+", " ");
    }
}