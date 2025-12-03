package es.altia.bne.postulante.application.service;

/**
 * Servicio encargado de obtener la cantidad de visitas
 * que ha recibido el CV del postulante por parte de empresas.
 *
 * Forma parte de los indicadores del Home del postulante.
 *
 * @author nelson.neculhueque
 */
public interface CvVisitasService {

    /**
     * Retorna la cantidad total de visitas al CV del postulante.
     *
     * @param idPostulante ID del postulante
     * @return número de visitas realizadas por empresas
     */
    int obtenerCantidadVisitas(Long idPostulante);
}
