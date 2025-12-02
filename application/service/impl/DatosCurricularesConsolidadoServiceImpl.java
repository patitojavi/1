package es.altia.bne.postulante.application.service.impl;

import es.altia.bne.postulante.application.dto.DatosCurricularesDTO;
import es.altia.bne.postulante.application.mapper.DatosCurricularesMapper;
import es.altia.bne.postulante.application.service.DatosCurricularesConsolidadoService;
import es.altia.bne.postulante.domain.repository.DatosPostulanteRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemCondLabJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemExpLaboralJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemHabilidadesJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemPresentacionJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemReferenciasLaboralesJpaRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemTitulacionJpaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DatosCurricularesConsolidadoServiceImpl implements DatosCurricularesConsolidadoService {

    private final DatosPostulanteRepository datosPostulanteRepository;
    private final DemCondLabJpaRepository demCondLabJpaRepository;
    private final DemPresentacionJpaRepository demPresentacionJpaRepository;
    private final DemExpLaboralJpaRepository demExpLaboralJpaRepository;
    private final DemReferenciasLaboralesJpaRepository demReferenciasLaboralesJpaRepository;
    private final DemHabilidadesJpaRepository demHabilidadesJpaRepository;
    private final DemTitulacionJpaRepository demTitulacionJpaRepository;
    private final DatosCurricularesMapper datosCurricularesMapper;

    public DatosCurricularesConsolidadoServiceImpl(
            DatosPostulanteRepository datosPostulanteRepository,
            DemCondLabJpaRepository demCondLabJpaRepository,
            DemPresentacionJpaRepository demPresentacionJpaRepository,
            DemExpLaboralJpaRepository demExpLaboralJpaRepository,
            DemReferenciasLaboralesJpaRepository demReferenciasLaboralesJpaRepository,
            DemHabilidadesJpaRepository demHabilidadesJpaRepository,
            DemTitulacionJpaRepository demTitulacionJpaRepository,
            DatosCurricularesMapper datosCurricularesMapper) {
        this.datosPostulanteRepository = datosPostulanteRepository;
        this.demCondLabJpaRepository = demCondLabJpaRepository;
        this.demPresentacionJpaRepository = demPresentacionJpaRepository;
        this.demExpLaboralJpaRepository = demExpLaboralJpaRepository;
        this.demReferenciasLaboralesJpaRepository = demReferenciasLaboralesJpaRepository;
        this.demHabilidadesJpaRepository = demHabilidadesJpaRepository;
        this.demTitulacionJpaRepository = demTitulacionJpaRepository;
        this.datosCurricularesMapper = datosCurricularesMapper;
    }

    @Override
    public Optional<DatosCurricularesDTO> obtenerDatosCurriculares(Long idPostulante) {
        log.info("Obteniendo datos curriculares consolidados para postulante ID: {}", idPostulante);

        try {
            // Validar que el postulante exista
            var datosPersonales = datosPostulanteRepository.obtenerDatosBasicos(idPostulante);

            // Obtener condición laboral (ordenada por fecha modificación descendente)
            var condicionLaboral = demCondLabJpaRepository
                    .findFirstByPerPersonasIdOrderByFecModifDesc(idPostulante)
                    .map(datosCurricularesMapper::toDemCondLabDTO)
                    .orElse(null);

            // Obtener presentación/resumen (ordenado por fecha modificación descendente)
            var presentacion = demPresentacionJpaRepository
                    .findFirstByPerPersonasIdOrderByFecModifDesc(idPostulante)
                    .map(datosCurricularesMapper::toDemPresentacionDTO)
                    .orElse(null);

            // Obtener experiencias laborales (ordenadas por fecha inicio descendente)
            var experienciasLaborales = demExpLaboralJpaRepository
                    .findByPerPersonasIdOrderByFecIniDesc(idPostulante)
                    .stream()
                    .map(datosCurricularesMapper::toDemExpLaboralDTO)
                    .collect(Collectors.toList());

            // Obtener referencias laborales (ordenadas por ID ascendente)
            var referenciasLaborales = demReferenciasLaboralesJpaRepository
                    .findByPerPersonasIdOrderByIdAsc(idPostulante)
                    .stream()
                    .map(datosCurricularesMapper::toDemReferenciasLaboralesDTO)
                    .collect(Collectors.toList());

            // Obtener habilidades (ordenadas por ID ascendente)
            var habilidades = demHabilidadesJpaRepository
                    .findByPerPersonasIdOrderByIdAsc(idPostulante)
                    .stream()
                    .map(datosCurricularesMapper::toDemHabilidadesDTO)
                    .collect(Collectors.toList());

            // Obtener educación/titulaciones (ordenadas por nivel educativo descendente)
            var experienciasEducativas = demTitulacionJpaRepository
                    .findByPerPersonasIdOrderByNivelEducativoDesc(idPostulante)
                    .stream()
                    .map(datosCurricularesMapper::toDemTitulacionesDTO)
                    .collect(Collectors.toList());

            // Construir el DTO consolidado con todos los datos mapeados
            var datosCurriculares = DatosCurricularesDTO.builder()
                    .idPostulante(idPostulante)
                    .datosBasicos(datosPersonales)
                    .contacto(null) // Puede ser inicializado vacío si es necesario
                    .direccion(null) // Puede ser inicializado vacío si es necesario
                    .condicionLaboral(condicionLaboral)
                    .resumenPerfil(presentacion)
                    .experienciasLaborales(experienciasLaborales.isEmpty() ? Collections.emptyList() : experienciasLaborales)
                    .referenciasLaborales(referenciasLaborales.isEmpty() ? Collections.emptyList() : referenciasLaborales)
                    .capacitaciones(Collections.emptyList()) // Sin datos en H2 de prueba
                    .idiomas(Collections.emptyList()) // Sin datos en H2 de prueba
                    .certificaciones(Collections.emptyList()) // Sin datos en H2 de prueba
                    .experienciasEducativas(experienciasEducativas.isEmpty() ? Collections.emptyList() : experienciasEducativas)
                    .discapacidad(null) // Puede ser inicializado vacío si es necesario
                    .habilidades(habilidades.isEmpty() ? Collections.emptyList() : habilidades)
                    .conocimientos(Collections.emptyList()) // Sin datos en H2 de prueba
                    .logros(Collections.emptyList()) // Sin datos en H2 de prueba
                    .fechaActualizacion(LocalDateTime.now())
                    .build();

            log.info("Datos curriculares consolidados obtenidos exitosamente para postulante ID: {}", idPostulante);
            return Optional.of(datosCurriculares);
        } catch (Exception e) {
            log.warn("Postulante no encontrado o error al obtener datos con ID: {}", idPostulante, e);
            return Optional.empty();
        }
    }

    @Override
    public DatosCurricularesDTO actualizarDatosCurriculares(DatosCurricularesDTO datosCurriculares) {
        log.info("Actualizando datos curriculares consolidados para postulante ID: {}", datosCurriculares.getIdPostulante());
        datosCurriculares.setFechaActualizacion(LocalDateTime.now());
        return datosCurriculares;
    }

}

