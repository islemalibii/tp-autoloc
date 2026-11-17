package tn.esprit.autoloc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.enums.StatutReservation;
import tn.esprit.autoloc.service.observer.ReservationSubject;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationSubject reservationSubject;

    private Client client() {
        Client c = new Client();
        c.setNom("Ben Ali");
        c.setEmail("sami@mail.com");
        c.setTelephone("20111222");
        return c;
    }

    @Test
    void creerReservation_notifieEmailEtSms(CapturedOutput output) {
        Reservation r = reservationService.creerReservation(client());

        assertEquals(StatutReservation.CONFIRMEE, r.getStatut());
        assertTrue(output.getOut().contains("[EMAIL] Confirmation envoyee au client sami@mail.com"));
        assertTrue(output.getOut().contains("[SMS] Confirmation envoyee au 20111222"));
    }

    @Test
    void annulerReservation_notifieEmailEtSms(CapturedOutput output) {
        Reservation r = reservationService.creerReservation(client());

        reservationService.annulerReservation(r);

        assertEquals(StatutReservation.ANNULEE, r.getStatut());
        assertTrue(output.getOut().contains("[EMAIL] Annulation notifiee au client sami@mail.com"));
        assertTrue(output.getOut().contains("[SMS] Annulation notifiee au 20111222"));
    }

    @Test
    void sujetDirect_notifieTousLesObservateurs(CapturedOutput output) {
        Reservation r = new Reservation();
        r.setClient(client());

        reservationSubject.notifierConfirmation(r);

        assertTrue(output.getOut().contains("[EMAIL]"));
        assertTrue(output.getOut().contains("[SMS]"));
    }
}