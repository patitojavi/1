package es.altia.bne.postulante.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.db.DBAccessException;
import es.altia.bne.postulante.application.dto.ConsultaPersonaDTO;
import es.altia.bne.postulante.application.service.ConsultaPersonaService;
import es.altia.bne.postulante.domain.repository.DatosPostulanteRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas;
import jakarta.persistence.NoResultException;
import lombok.extern.log4j.Log4j2;

/**
 * Implementación del servicio para consultar personas por documento.
 *
 * @author gaspar.jimenez
 */
@Service
@Log4j2
public class ConsultaPersonaServiceImpl implements ConsultaPersonaService {

    @Autowired
    private DatosPostulanteRepository datosPostulanteRepository;

    @Override
    public ConsultaPersonaDTO consultarPersonaPorRut(Integer tipoDocumento, String numDocumento, String digitoVerificador) throws ServiceException {
        try {
            log.info("Consultando persona por documento: tipo={}, numero={}, dv={}", tipoDocumento, numDocumento, digitoVerificador);
            
            // Validar parámetros obligatorios
            if (tipoDocumento == null || numDocumento == null || numDocumento.trim().isEmpty()) {
                return new ConsultaPersonaDTO(false, "Tipo de documento y número de documento son obligatorios");
            }

            // Usar el número de documento tal como viene, preservando su formato original
            String numeroDocumento = numDocumento.trim();
            
            // Usar el dígito verificador proporcionado o null si no se envía
            String dvCalculado = digitoVerificador;

            // Buscar la persona en la base de datos
            try {
                PerPersonas persona = null;
                
                // Determinar qué método de búsqueda usar según si se proporciona dígito verificador
                if (dvCalculado != null && !dvCalculado.trim().isEmpty()) {
                    // Buscar con dígito verificador específico
                    persona = datosPostulanteRepository.buscarPersonaPorRutConDv(tipoDocumento, numeroDocumento, dvCalculado.trim());
                } else {
                    // Intentar buscar sin dígito verificador (cuando está vacío o null en la BD)
                    try {
                        persona = datosPostulanteRepository.buscarPersonaPorRutSinDv(tipoDocumento, numeroDocumento);
                    } catch (NoResultException e1) {
                        // Si no encuentra sin DV, intentar buscar cualquier persona con ese tipo y número
                        persona = datosPostulanteRepository.buscarPersonaPorRut(tipoDocumento, numeroDocumento);
                    }
                }
                
                // Crear respuesta con los datos de la persona encontrada
                return new ConsultaPersonaDTO(
                    persona.getId(),
                    persona.getNumDocumento(),
                    persona.getDigitoVerificador(),
                    persona.getTipoDocumento(),
                    persona.getNombre(),
                    persona.getApe1(),
                    persona.getApe2(),
                    persona.getEmail(),
                    persona.getEstado()
                );

            } catch (NoResultException e) {
                log.debug("No se encontró persona con documento: {}", numeroDocumento);
                ConsultaPersonaDTO response = new ConsultaPersonaDTO(false, "No se encontró una persona registrada con este documento");
                response.setNumDocumento(numeroDocumento);
                response.setDigitoVerificador(dvCalculado);
                response.setTipoDocumento(tipoDocumento);
                return response;
            }

        } catch (DBAccessException e) {
            log.error("Error de acceso a base de datos al consultar persona por documento: {}", e.getMessage());
            throw new ServiceException("Error interno del servidor al consultar la persona", e);
        } catch (Exception e) {
            log.error("Error inesperado al consultar persona por documento: {}", e.getMessage(), e);
            throw new ServiceException("Error inesperado al consultar la persona", e);
        }
    }
}