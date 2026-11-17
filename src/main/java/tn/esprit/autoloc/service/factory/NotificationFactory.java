package tn.esprit.autoloc.service.factory;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.service.observer.EmailNotificationObserver;
import tn.esprit.autoloc.service.observer.IReservationObserver;
import tn.esprit.autoloc.service.observer.SmsNotificationObserver;

@Component
public class NotificationFactory{
    private final EmailNotificationObserver emailNotificationObserver;
    private final SmsNotificationObserver smsNotificationObserver;
    public NotificationFactory(EmailNotificationObserver emailNotificationObserver,
                               SmsNotificationObserver smsNotificationObserver){
        this.emailNotificationObserver = emailNotificationObserver;
        this.smsNotificationObserver = smsNotificationObserver;
    }
    public IReservationObserver creerObservateur(String canal) {
        if (canal == null) {
            throw new IllegalArgumentException("Le canal de notification est obligatoire");
        }
        return switch (canal.toUpperCase()) {
            case "EMAIL" -> emailNotificationObserver;
            case "SMS" -> smsNotificationObserver;
            default -> throw new IllegalArgumentException("Canal de notification inconnu : " + canal);
        };
    }
}