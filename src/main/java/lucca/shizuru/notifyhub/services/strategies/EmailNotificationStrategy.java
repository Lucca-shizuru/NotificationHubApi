package lucca.shizuru.notifyhub.services.strategies;


import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(Notification notification) {
        //simulação do envio do email por enquanto
        System.out.println("Enviando EMAIL para: " + notification.getDestination());
        System.out.println("Conteúdo: " + notification.getContent());
    }

    @Override
    public boolean isApplicable(NotificationChannel chanel) {
        return NotificationChannel.EMAIL.equals(chanel);
    }


}
