package lucca.shizuru.notifyhub.services;

import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationStatus;
import lucca.shizuru.notifyhub.repositories.NotificationRepository;
import lucca.shizuru.notifyhub.services.strategies.NotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @Mock
    private NotificationStrategy emailStrategy;

    private NotificationService service;

    @BeforeEach
    void setup() {

        service = new NotificationService(repository, List.of(emailStrategy));
    }

    @Test
    @DisplayName("Deve processar notificação com sucesso")
    void deveProcessarComSucesso() {
        Notification notification = new Notification();

        notification.setChannel(lucca.shizuru.notifyhub.domain.enums.NotificationChannel.EMAIL);


        when(emailStrategy.isApplicable(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(notification);

        service.scheduleNotification(notification);

        assertEquals(NotificationStatus.SENT, notification.getStatus());
        verify(emailStrategy, times(1)).sendNotification(notification);
    }
}