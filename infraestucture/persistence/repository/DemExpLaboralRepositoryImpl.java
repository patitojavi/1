package es.altia.bne.postulante.infraestucture.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.altia.bne.common.db.BaseAbstractRepository;
import es.altia.bne.postulante.domain.model.DemExpLaboral;
import es.altia.bne.postulante.domain.repository.DemExpLaboralRepository;
import es.altia.bne.postulante.infraestucture.persistence.repository.jpa.DemExpLaboralJpaRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository("demExpLaboralRepositoryImpl")
@Transactional
public class DemExpLaboralRepositoryImpl extends BaseAbstractRepository implements DemExpLaboralRepository {

    private final DemExpLaboralJpaRepository jpaRepository;

    public DemExpLaboralRepositoryImpl(DemExpLaboralJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<DemExpLaboral> findByIdPostulanteOrderByFecIniDesc(Long idPostulante) {
        log.debug("Buscando experiencias laborales para el postulante con ID: {}", idPostulante);
        var entities = jpaRepository.findByPerPersonasIdOrderByFecIniDesc(idPostulante);
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByIdAndIdPostulante(Long id, Long idPostulante) {
        return jpaRepository.existsByIdAndPerPersonasId(id, idPostulante);
    }

    @Override
    public DemExpLaboral save(DemExpLaboral entity) {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad a guardar no puede ser null");
        }
        var savedEntity = jpaRepository.save(toEntity(entity));
        return toDomain(savedEntity);
    }

    @Override
    public void delete(DemExpLaboral entity) {
        if (entity == null) {
            throw new IllegalArgumentException("La entidad a eliminar no puede ser null");
        }
        jpaRepository.delete(toEntity(entity));
    }

    @Override
    public Optional<DemExpLaboral> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<DemExpLaboral> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private DemExpLaboral toDomain(es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral entity) {
        if (entity == null) return null;
        
        return DemExpLaboral.builder()
            .id(entity.getId())
            .idPostulante(entity.getPerPersonas() != null ? entity.getPerPersonas().getId() : null)
            .idOcupacion(entity.getBneOcupaciones() != null ? entity.getBneOcupaciones().getId() : null)
            .idRegion(entity.getBneRegiones() != null ? entity.getBneRegiones().getId() : null)
            .nombreRegion(entity.getBneRegiones() != null ? entity.getBneRegiones().getNombre() : null)
            .razonSocial(entity.getRazonSocial())
            .fecIni(entity.getFecIni())
            .fecFin(entity.getFecFin())
            .numMeses(entity.getNumMeses())
            .sueldo(entity.getSueldo())
            .descripcion(entity.getDescripcion())
            .codFichero(entity.getDemExpLaboralArchivos() != null ? entity.getDemExpLaboralArchivos().getId() : null)
            .fecAlta(entity.getFecAlta())
            .fecModif(entity.getFecModif())
            .referencias(entity.getReferencias())
            .otrasOcupaciones(entity.getOtrasOcupaciones())
            .build();
    }

    private es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral toEntity(DemExpLaboral domain) {
        if (domain == null) return null;

        var entity = new es.altia.bne.postulante.infraestucture.persistence.repository.entity.DemExpLaboral();
        entity.setId(domain.getId());
        
        if (domain.getIdPostulante() != null) {
            var perPersonas = new es.altia.bne.postulante.infraestucture.persistence.repository.entity.PerPersonas(domain.getIdPostulante());
            entity.setPerPersonas(perPersonas);
        }
        
        if (domain.getIdOcupacion() != null) {
            var bneOcupaciones = new es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneOcupaciones(domain.getIdOcupacion());
            entity.setBneOcupaciones(bneOcupaciones);
        }
        
        if (domain.getIdRegion() != null) {
            var bneRegiones = new es.altia.bne.postulante.infraestucture.persistence.repository.entity.BneRegiones(domain.getIdRegion());
            entity.setBneRegiones(bneRegiones);
        }
        
        entity.setRazonSocial(domain.getRazonSocial());
        entity.setFecIni(domain.getFecIni());
        entity.setFecFin(domain.getFecFin());
        entity.setNumMeses(domain.getNumMeses());
        entity.setSueldo(domain.getSueldo());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFecAlta(domain.getFecAlta());
        entity.setFecModif(domain.getFecModif());
        entity.setReferencias(domain.getReferencias());
        entity.setOtrasOcupaciones(domain.getOtrasOcupaciones());

        return entity;
    }
}