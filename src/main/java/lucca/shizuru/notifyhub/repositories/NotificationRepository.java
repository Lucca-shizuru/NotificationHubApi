package lucca.shizuru.notifyhub.repositories;

import lucca.shizuru.notifyhub.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
