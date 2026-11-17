package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.web.dto.PaiementDTO;

public interface IPaiementService {
    PaiementDTO create(PaiementDTO paiement);
    PaiementDTO findById(Long id);
    List<PaiementDTO> findAll();
    PaiementDTO update(Long id, PaiementDTO paiement);
    void deleteById(Long id);
}
