package tn.esprit.autoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.web.dto.ContratDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContratMapper {

    @Mapping(source = "idContrat", target = "id")
    @Mapping(source = "reservation.idReservation", target = "reservationId")
    ContratDTO toDto(Contrat contrat);

    @Mapping(source = "id", target = "idContrat")
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    Contrat toEntity(ContratDTO dto);
}