package es.altia.bne.postulante.infraestucture.persistence.repository.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BNE_NIVELES_EDUCATIVOS", uniqueConstraints = @UniqueConstraint(columnNames = "CODIGO"))
public class NivelesEducativos {

    @Id
    @Getter
    @Setter
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "CODIGO", nullable = false, updatable = false)
    private String codigo;

    @Getter
    @Setter
    @Column(name = "NOMBRE", nullable = false, updatable = false)
    private String nombre;

    @Getter
    @Setter
    @Column(name = "FEC_INI_VIG", nullable = true, updatable = false)
    private Date fecIniVig;

    @Getter
    @Setter
    @Column(name = "FEC_FIN_VIG", nullable = true, updatable = false)
    private Date fecFinVig;

}
