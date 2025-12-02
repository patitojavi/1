package es.altia.bne.postulante.application.service;


import es.altia.bne.common.exception.ResourceNotFoundException;
import es.altia.bne.common.exception.ServiceException;
import es.altia.bne.common.exception.validation.DataValidationException;
import es.altia.bne.postulante.application.service.model.CvFormato;

public interface CurriculumDocumentoService {

    byte[] generarCv(Long idPostulante, CvFormato formato)
            throws DataValidationException, ResourceNotFoundException, ServiceException;
}