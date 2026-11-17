package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

public interface IVehiculeService {
    VehiculeDTO create(VehiculeDTO vehicule);
    VehiculeDTO findById(Long id);
    List<VehiculeDTO> findAll();
    VehiculeDTO update(Long id, VehiculeDTO vehicule);
    void deleteById(Long id);
    void affecterVehiculeAAgence(Long vehiculeId, Long agenceId);

    void desaffecterVehiculeDeAgence(Long vehiculeId);
}
