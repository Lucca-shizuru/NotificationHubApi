package lucca.shizuru.notifyhub.factory;


import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.dto.NotificationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    public Notification createNotification (NotificationRequestDto dto){
        Notification notification = new Notification();
        notification.setContent(dto.content());
        notification.setChannel(dto.channel());
        notification.setPriority(dto.priority());
        notification.setDestination(dto.destination());

        return notification;
    }

}
