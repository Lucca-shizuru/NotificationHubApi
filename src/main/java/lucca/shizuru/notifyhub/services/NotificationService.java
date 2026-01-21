package lucca.shizuru.notifyhub.services;

import jakarta.transaction.Transactional;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification ScheduleNotification(Notification notification) {

        return notificationRepository.save(notification);
    }

    public List<Notification> GetNotifications() {
        return notificationRepository.findAll();
    }
}
