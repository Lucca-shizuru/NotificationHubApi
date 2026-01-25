package lucca.shizuru.notifyhub.factory;


import lucca.shizuru.notifyhub.domain.entities.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationStatus;
import lucca.shizuru.notifyhub.dto.NotificationRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationFactory {
    public Notification createNotification (NotificationRequestDto dto){
        Notification notification = new Notification();
        notification.setContent(dto.content());
        notification.setChannel(dto.channel());
        notification.setPriority(dto.priority());
        notification.setDestination(dto.destination());

        notification.setStatus(NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());

        return notification;
    }

}
