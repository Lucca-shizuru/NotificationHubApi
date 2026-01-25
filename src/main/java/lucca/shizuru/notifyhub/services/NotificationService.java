package lucca.shizuru.notifyhub.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationStatus;
import lucca.shizuru.notifyhub.repositories.NotificationRepository;
import lucca.shizuru.notifyhub.services.strategies.NotificationStrategy;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final List<NotificationStrategy> strategies;

    public NotificationService(NotificationRepository notificationRepository, List<NotificationStrategy> strategies) {
        this.notificationRepository = notificationRepository;
        this.strategies = strategies;
    }

    @Transactional
    public Notification scheduleNotification(Notification notification) {
        log.info("Iniciando processo de notificação para canal: {}", notification.getChannel());
        strategies.stream()
                .filter(s -> s.isApplicable(notification.getChannel()))
                .findFirst()
                .ifPresentOrElse(
                        strategy -> {
                            log.debug("Estratégia definida: {}", strategy.getClass().getSimpleName());
                            strategy.validate(notification.getDestination());
                            strategy.sendNotification(notification);
                            notification.setStatus(NotificationStatus.SENT);
                        },
                        () -> {
                            log.error(" Erro: Nenhuma estratégia encontrada para o canal {}", notification.getChannel());
                            notification.setStatus(NotificationStatus.FAILED);
                        }
                );

        return notificationRepository.save(notification);
    }

    public List<Notification> GetNotifications() {
        return notificationRepository.findAll();
    }
}
