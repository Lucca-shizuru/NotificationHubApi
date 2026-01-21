package lucca.shizuru.notifyhub.services.strategies;


import lucca.shizuru.notifyhub.domain.Notification;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(Notification notification) {
        //simulação do envio do email por enquanto
        System.out.println("Enviando EMAIL para: " + notification.getDestination());
        System.out.println("Conteúdo: " + notification.getContent());
    }

    @Override
    public boolean isApplicable(String chanel) {
        return "Email".equalsIgnoreCase(chanel);
    }


}
