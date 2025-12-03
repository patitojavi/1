package es.altia.bne.postulante.infraestucture.persistence.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POSTULACIONES")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_POSTULANTE")
    private Long idPostulante;

    @Column(name = "ID_OFERTA")
    private Long idOferta;

    @Column(name = "ESTADO")
    private String estado;
}
