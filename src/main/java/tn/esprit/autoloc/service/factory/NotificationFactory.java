package tn.esprit.autoloc.service.factory;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.service.observer.EmailNotificationObserver;
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
    /*public ReservationObserver creerObservateur(String canal){
        // Code à Faire
    }*/
}