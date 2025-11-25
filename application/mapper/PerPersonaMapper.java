package es.altia.bne.postulante.application.mapper;

import org.springframework.stereotype.Component;

import es.altia.bne.postulante.application.dto.DatosBasicosDTO;
import es.altia.bne.postulante.application.dto.DatosContactoDTO;
import es.altia.bne.postulante.application.dto.DatosDireccionDTO;
import es.altia.bne.postulante.application.dto.DatosDiscapacidadDTO;
import es.altia.bne.postulante.application.dto.PerPersonaDTO;
import lombok.extern.log4j.Log4j2;

/**
 * Mapper para convertir PerPersonaDTO a DTOs específicos de endpoints.
 * 
 * Propósito:
 * - Separar responsabilidades: PerPersonaDTO es la estructura interna completa,
 *   mientras que los DTOs específicos exponen solo los campos necesarios por endpoint.
 * - Facilitar mantenimiento y pruebas: cambios en la estructura interna no afectan
 *   directamente los contratos de API.
 * - Validar y transformar datos según las necesidades de cada endpoint.
 * 
 * @author assistant
 */
@Component
@Log4j2
public class PerPersonaMapper {

    /**
     * Convierte PerPersonaDTO a DatosBasicosDTO.
     * 
     * Incluye: id, numDocumento, nombre, ape1, ape2, fecNac, sexo, nacionalidad,
     * estadoCivil, digitoVerificador, tipoDocumento, indMostrarDatospostulante.
     * 
     * @param perPersona objeto fuente con datos de persona completos
     * @return DatosBasicosDTO con campos básicos de identificación
     */
    public DatosBasicosDTO toBasicos(PerPersonaDTO perPersona) {
        if (perPersona == null) {
            log.warn("PerPersonaDTO nulo, retornando null en toBasicos");
            return null;
        }

        DatosBasicosDTO dto = new DatosBasicosDTO();
        dto.setId(perPersona.getId());
        dto.setNumDocumento(perPersona.getNumDocumento());
        dto.setNombre(perPersona.getNombre());
        dto.setApe1(perPersona.getApe1());
        dto.setApe2(perPersona.getApe2());
        dto.setFecNac(perPersona.getFecNac());
        dto.setSexo(perPersona.getSexo());
        dto.setIdNacionalidad(perPersona.getNacionalidad());
        dto.setIdEstadoCivil(perPersona.getEstadoCivil());
        dto.setDigitoVerificador(perPersona.getDigitoVerificador());
        dto.setTipoDocumento(perPersona.getTipoDocumento());
        dto.setIndMostrarDatospostulante(perPersona.getIndMostrarDatospostulante());

        log.debug("Mappeo PerPersonaDTO -> DatosBasicosDTO completado para ID: {}", perPersona.getId());
        return dto;
    }

    /**
     * Convierte PerPersonaDTO a DatosContactoDTO.
     * 
     * Incluye: idPostulante, telefonoNotificaciones, telefonoAlternativo,
     * notificacionesSMS, email.
     * 
     * @param perPersona objeto fuente con datos de persona completos
     * @return DatosContactoDTO con campos de contacto
     */
    public DatosContactoDTO toContacto(PerPersonaDTO perPersona) {
        if (perPersona == null) {
            log.warn("PerPersonaDTO nulo, retornando null en toContacto");
            return null;
        }

        DatosContactoDTO dto = new DatosContactoDTO();
        dto.setIdPostulante(perPersona.getId());
        dto.setTelefonoNotificaciones(perPersona.getTlfnoNotif());
        dto.setTelefonoAlternativo(perPersona.getTlfnoAlt());
        dto.setNotificacionesSMS(perPersona.getNotifSms());
        dto.setEmail(perPersona.getEmail());

        log.debug("Mappeo PerPersonaDTO -> DatosContactoDTO completado para ID: {}", perPersona.getId());
        return dto;
    }

    /**
     * Convierte PerPersonaDTO a DatosDireccionDTO.
     * 
     * Incluye: idPostulante, idRegion, nombreRegion, idComuna, nombreComuna,
     * calle, numero, casa, poblacion, indMostrarDiccion.
     * 
     * @param perPersona objeto fuente con datos de persona completos
     * @return DatosDireccionDTO con campos de dirección
     */
    public DatosDireccionDTO toDireccion(PerPersonaDTO perPersona) {
        if (perPersona == null) {
            log.warn("PerPersonaDTO nulo, retornando null en toDireccion");
            return null;
        }

        DatosDireccionDTO dto = new DatosDireccionDTO();
        dto.setIdPostulante(perPersona.getId());

        // Mapear región desde la entidad relacionada si existe
        if (perPersona.getBneRegiones() != null) {
            dto.setIdRegion(perPersona.getBneRegiones().getId());
            dto.setNombreRegion(perPersona.getBneRegiones().getNombre());
        }

        // Mapear comuna desde la entidad relacionada si existe
        if (perPersona.getBneComunas() != null) {
            dto.setIdComuna(perPersona.getBneComunas().getId());
            dto.setNombreComuna(perPersona.getBneComunas().getNombre());
        }

        dto.setCalle(perPersona.getCalle());
        dto.setNumero(perPersona.getNumero());
        dto.setCasa(perPersona.getCasa());
        dto.setPoblacion(perPersona.getPoblacion());
        dto.setIndMostrarDiccion(perPersona.getIndMostrarDiccion());

        log.debug("Mappeo PerPersonaDTO -> DatosDireccionDTO completado para ID: {}", perPersona.getId());
        return dto;
    }

    /**
     * Convierte PerPersonaDTO a DatosDiscapacidadDTO.
     * 
     * Incluye: idPostulante, gradoDiscapacidad, ajustePuestoTrabajo, mostrarDiscapacidad.
     * 
     * Nota: los campos gradoDiscapacidad y ajustePuestoTrabajo en PerPersonaDTO son String
     * pero en DatosDiscapacidadDTO son Boolean; se mapean manualmente.
     * 
     * @param perPersona objeto fuente con datos de persona completos
     * @return DatosDiscapacidadDTO con campos de discapacidad
     */
    public DatosDiscapacidadDTO toDiscapacidad(PerPersonaDTO perPersona) {
        if (perPersona == null) {
            log.warn("PerPersonaDTO nulo, retornando null en toDiscapacidad");
            return null;
        }

        DatosDiscapacidadDTO dto = new DatosDiscapacidadDTO();
        dto.setIdPostulante(perPersona.getId());
        
        // Convertir indDiscapacidad (String) a Boolean si es necesario
        dto.setGradoDiscapacidad(parseBoolean(perPersona.getIndDiscapacidad()));
        
        // Convertir ajustesPtoTrab (String) a Boolean si es necesario
        dto.setAjustePuestoTrabajo(parseBoolean(perPersona.getAjustesPtoTrab()));
        
        dto.setMostrarDiscapacidad(perPersona.getMostrarDiscapacidad());

        log.debug("Mappeo PerPersonaDTO -> DatosDiscapacidadDTO completado para ID: {}", perPersona.getId());
        return dto;
    }

    /**
     * Extrae el ID de sitio de referencia de PerPersonaDTO.
     * 
     * @param perPersona objeto fuente con datos de persona completos
     * @return ID del sitio de referencia, o null si no existe
     */
    public Integer toSitioReferencia(PerPersonaDTO perPersona) {
        if (perPersona == null) {
            log.warn("PerPersonaDTO nulo, retornando null en toSitioReferencia");
            return null;
        }

        Integer sitio = perPersona.getSitio();
        log.debug("Extracción sitio de referencia: {} para ID: {}", sitio, perPersona.getId());
        return sitio;
    }

    /**
     * Método auxiliar para convertir String a Boolean.
     * Acepta: "S", "Y", "true" como true, cualquier otro valor como false o null.
     * 
     * @param value valor String a convertir
     * @return Boolean true si es "S", "Y" o "true" (case-insensitive), false en otro caso
     */
    private Boolean parseBoolean(String value) {
        if (value == null) {
            return null;
        }
        return value.equalsIgnoreCase("S") || value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("true");
    }
}
