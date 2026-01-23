package lucca.shizuru.notifyhub.services.strategies;

import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Enviando SMS para o celular: " + notification.getDestination());
        System.out.println("Mensagem: " + notification.getContent());

    }

    @Override
    public boolean isApplicable(NotificationChannel channel) {
        return NotificationChannel.SMS.equals(channel);
    }

    @Override
    public void validate(String destination) {

        if (destination == null || !destination.matches("\\d{10,11}")){
            throw new IllegalArgumentException("Para SMS, o destino deve ser um número de telefone válido (apenas dígitos).");
        }

    }


}
