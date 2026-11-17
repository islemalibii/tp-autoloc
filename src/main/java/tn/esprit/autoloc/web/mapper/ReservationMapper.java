package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ReservationResponseDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    @Mapping(target = "clientId", source = "client.idClient")
    @Mapping(target = "clientNomComplet",
            expression = "java(reservation.getClient().getPrenom() + \" \" + reservation.getClient().getNom())")
    @Mapping(target = "vehiculeId", source = "vehicule.idVehicule")
    @Mapping(target = "vehiculeImmatriculation", source = "vehicule.immatriculation")
    @Mapping(target = "tarifEstime", ignore = true)
    ReservationResponseDTO toDto(Reservation reservation);
}