package es.altia.bne.postulante.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvitacionSCTDTO {
    private Long id;
    private String empresa;
    private LocalDate fechaInvitacion;
}
