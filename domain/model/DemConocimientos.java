package es.altia.bne.postulante.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEM_CONOCIMIENTOS")
public class DemConocimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_POSTULANTE", nullable = false)
    private Long idPostulante;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @Column(name = "NIVEL_DOMINIO")
    private Integer nivelDominio;

    @Column(name = "CERTIFICACION", length = 100)
    private String certificacion;

    @Column(name = "ACTUALIDAD")
    private Boolean actualidad;
}