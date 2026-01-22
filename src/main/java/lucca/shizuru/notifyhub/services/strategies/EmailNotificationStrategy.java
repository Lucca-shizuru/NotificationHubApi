package lucca.shizuru.notifyhub.services.strategies;


import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Enviando EMAIL para: " + notification.getDestination());
        System.out.println("Conteúdo: " + notification.getContent());
    }

    @Override
    public boolean isApplicable(NotificationChannel channel) {
        return NotificationChannel.EMAIL.equals(channel);
    }


}
