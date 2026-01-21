package lucca.shizuru.notifyhub.services.strategies;

import lucca.shizuru.notifyhub.domain.Notification;

public interface NotificationStrategy {
    void sendNotification(Notification notification);

    boolean isApplicable(String chanel);
}
