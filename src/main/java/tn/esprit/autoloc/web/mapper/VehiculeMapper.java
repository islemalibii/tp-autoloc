package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculeMapper {

    VehiculeDTO toDto(Vehicule vehicule);
    Vehicule toEntity(VehiculeDTO dto);
}