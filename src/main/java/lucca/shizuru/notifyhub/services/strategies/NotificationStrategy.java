package lucca.shizuru.notifyhub.services.strategies;

import lucca.shizuru.notifyhub.domain.entities.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;

public interface NotificationStrategy {
    void sendNotification(Notification notification);

    boolean isApplicable(NotificationChannel channel);
    void validate(String destination);
}

