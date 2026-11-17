package tn.esprit.autoloc.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContratDTO {
    private Long id;
    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private Boolean valide;
    private Long reservationId;
}