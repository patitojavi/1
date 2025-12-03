package es.altia.bne.postulante.infraestucture.persistence.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POSTULACIONES_VISTAS")
public class PostulacionVista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_POSTULANTE")
    private Long idPostulante;

    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;
}
