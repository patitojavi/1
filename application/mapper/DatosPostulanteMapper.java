package es.altia.bne.postulante.application.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;

import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;

/**
 * Mapper de conversión entre la entidad {@link PerPersonas}
 * y el DTO {@link PerPersonaDTO}.
 *
 * Incluye mapeos auxiliares para direccion, contacto y discapacidad.
 */
@Mapper(componentModel = "spring", uses = {BneComunasMapper.class, BneRegionesMapper.class})
public interface DatosPostulanteMapper {

    // ============================================================
    // === ENTIDAD → DTO ==========================================
    // ============================================================

    @Mapping(source = "id", target = "id")
    @Mapping(source = "numDocumento", target = "numDocumento")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "ape1", target = "ape1")
    @Mapping(source = "ape2", target = "ape2")
    @Mapping(source = "fecNac", target = "fecNac")
    @Mapping(source = "sexo", target = "sexo")
    @Mapping(source = "idNacionalidad", target = "nacionalidad")
    @Mapping(source = "idEstadoCivil", target = "estadoCivil")
    @Mapping(source = "tlfnoNotif", target = "tlfnoNotif")
    @Mapping(source = "tlfnoAlt", target = "tlfnoAlt")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "indDiscapacidad", target = "indDiscapacidad")
    @Mapping(source = "ajustesPtoTrab", target = "ajustesPtoTrab")
    @Mapping(source = "mostrarDiscapacidad", target = "mostrarDiscapacidad")
    @Mapping(source = "bneComunas", target = "bneComunas")
    @Mapping(source = "bneRegiones", target = "bneRegiones")
    @Mapping(source = "bneNivelesEducativos", target = "bneNivelesEducativos")
    @Mapping(source = "indMostrarDatospostulante", target = "indMostrarDatospostulante")
    @Mapping(source = "esFcs", target = "esFcs")
    @Mapping(source = "cvCompleto", target = "cvCompleto")
    @Mapping(source = "sitio", target = "sitio")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PerPersonaDTO entityToDto(PerPersonas entity);

    // ============================================================
    // === DTO → ENTIDAD ==========================================
    // ============================================================

    @Mapping(source = "id", target = "id")
    @Mapping(source = "numDocumento", target = "numDocumento")
    @Mapping(source = "tipoDocumento", target = "tipoDocumento")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "ape1", target = "ape1")
    @Mapping(source = "ape2", target = "ape2")
    @BeanMapping(ignoreByDefault = true)
    PerPersonas dtoToEntity(PerPersonaDTO persona);

    // ============================================================
    // === MAPEOS PARCIALES =======================================
    // ============================================================

    @Named("postulanteDesdeDireccion")
    @Mapping(source = "idRegion", target = "bneRegiones", qualifiedByName = "regionDesdeId")
    @Mapping(source = "idComuna", target = "bneComunas", qualifiedByName = "comunaDesdeId")
    @BeanMapping(ignoreByDefault = true)
    PerPersonas direccionToPersona(DatosDireccionDTO direccionDTO);

    @Mapping(source = "idPostulante", target = "id")
    @Mapping(source = "telefonoNotificaciones", target = "tlfnoNotif")
    @Mapping(source = "telefonoAlternativo", target = "tlfnoAlt")
    @Mapping(source = "notificacionesSMS", target = "notifSms")
    @BeanMapping(ignoreByDefault = true)
    PerPersonas contactoToPersona(DatosContactoDTO contactoDTO);

    @Mapping(source = "idPostulante", target = "id")
    @Mapping(source = "gradoDiscapacidad", target = "indDiscapacidad")
    @Mapping(source = "ajustePuestoTrabajo", target = "ajustesPtoTrab")
    @Mapping(source = "mostrarDiscapacidad", target = "mostrarDiscapacidad")
    @BeanMapping(ignoreByDefault = true)
    PerPersonas discapacidadToPersona(DatosDiscapacidadDTO discapacidadDTO);
}
