package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ENCUESTA_PROFILING")
@Data
public class EncuestaProfiling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_POSTULANTE", nullable = false)
    private Long idPostulante;

    @Column(name = "ENCUESTA_PENDIENTE", nullable = false)
    private Boolean encuestaPendiente;

    @Column(name = "FEC_MODIF")
    private LocalDateTime fechaModificacion;
}
