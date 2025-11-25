package es.altia.bne.postulante.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitacionSCTDTO {
    private Long idInvitacion;
    private String empresa;
    private String cargo;
    private String estado;
}
