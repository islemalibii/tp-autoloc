package tn.esprit.autoloc.web.dto;

import java.math.BigDecimal;
import lombok.*;
import tn.esprit.autoloc.domain.enums.CategorieVehicule;
import tn.esprit.autoloc.domain.enums.StatutVehicule;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VehiculeDTO {
    private Long id;
    private String immatriculation;
    private String marque;
    private String modele;
    private CategorieVehicule categorie;
    private BigDecimal tarifJournalier;
    private StatutVehicule statut;
    private Long agenceId;
}