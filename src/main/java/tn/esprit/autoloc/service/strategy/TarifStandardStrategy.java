package tn.esprit.autoloc.service.strategy;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Component
public class TarifStandardStrategy implements ITarificationStrategy {
    @Override
    public BigDecimal calculerTarif(Vehicule vehicule, Reservation reservation) {

        long jours = Math.max(1,
                ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin()));
        BigDecimal base = vehicule.getTarifJournalier().multiply(BigDecimal.valueOf(jours));
        return base.setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    public String getCode() {
        return "STANDARD";
    }
}