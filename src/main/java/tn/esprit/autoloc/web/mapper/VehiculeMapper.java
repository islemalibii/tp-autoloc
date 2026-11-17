package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculeMapper {

    @Mapping(source = "idVehicule", target = "id")
    @Mapping(source = "agence.idAgence", target = "agenceId")
    VehiculeDTO toDto(Vehicule vehicule);

    @Mapping(source = "id", target = "idVehicule")
    @Mapping(target = "agence", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    Vehicule toEntity(VehiculeDTO dto);
}