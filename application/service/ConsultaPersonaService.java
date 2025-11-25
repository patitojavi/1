package es.altia.bne.postulante.application.service;

import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.postulante.application.dto.ConsultaPersonaDTO;

/**
 * Servicio para consultar personas por documento.
 *
 * @author gaspar.jimenez
 */
public interface ConsultaPersonaService {

    /**
     * Consulta si existe una persona con el documento especificado.
     *
     * @param tipoDocumento Tipo de documento (1 = RUT, 2 = Pasaporte, etc.)
     * @param numDocumento Número de documento (puede contener letras y números)
     * @param digitoVerificador Dígito verificador del documento (opcional)
     * @return ConsultaPersonaDTO con la información de la persona encontrada
     * @throws ServiceException Si ocurre un error en la consulta
     */
    ConsultaPersonaDTO consultarPersonaPorRut(Integer tipoDocumento, String numDocumento, String digitoVerificador) throws ServiceException;
}