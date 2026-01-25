package lucca.shizuru.notifyhub.services.strategies;

import lombok.extern.slf4j.Slf4j;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SmsNotificationStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(Notification notification) {
        log.info("Enviando SMS para: {} | Mensagem: {}",
                notification.getDestination(),
                notification.getContent());

    }

    @Override
    public boolean isApplicable(NotificationChannel channel) {
        return NotificationChannel.SMS.equals(channel);
    }

    @Override
    public void validate(String destination) {

        if (destination == null || !destination.matches("\\d{10,11}")){
            log.warn(" Falha na validação do SMS: destino inválido -> {}", destination);
            throw new IllegalArgumentException("Para SMS, o destino deve conter apenas numeros (DDD + Numero).");
        }

    }


}
