package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc.web.dto.ReservationResponseDTO;

public interface IReservationService {
    ReservationResponseDTO create(ReservationRequestDTO reservation);
    ReservationResponseDTO findById(Long id);
    List<ReservationResponseDTO> findAll();
    ReservationResponseDTO update(Long id, ReservationRequestDTO reservation);
    void deleteById(Long id);
}

