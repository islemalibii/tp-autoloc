package tn.esprit.autoloc.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.enums.StatutReservation;
import tn.esprit.autoloc.service.observer.ReservationSubject;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationSubject reservationSubject;
    /**
     * Crée une réservation et notifie tous les observateurs
     */
    public Reservation creerReservation(Client client) {
        Reservation reservation = Reservation.builder()
                .client(client)
                .dateDebut(LocalDate.now())
                .statut(StatutReservation.CONFIRMEE)
                .build();
        // Déclenche le process métier via le pattern Observer
        reservationSubject.notifierConfirmation(reservation);
        return reservation;
    }
    /**
     * Annule une réservation existante et notifie tous les observateurs
     */
    public Reservation annulerReservation(Reservation reservation) {
        reservation.setStatut(StatutReservation.ANNULEE);
        log.info("Reservation #{} annulée pour le client {}",
                reservation.getIdReservation(), reservation.getClient().getEmail());
        reservationSubject.notifierAnnulation(reservation);
        return reservation;
    }
}