package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INVITACIONES_SCT")
@Getter
@Setter
public class InvitacionSCT {

    @Id
    private Long id;

    @Column(name = "ID_POSTULANTE")
    private Long idPostulante;

    @Column(name = "NOMBRE_EMPRESA")
    private String nombreEmpresa;

    @Column(name = "FEC_INVITACION")
    private LocalDate fechaInvitacion;

    @Column(name = "ESTADO")
    private String estado;
}
