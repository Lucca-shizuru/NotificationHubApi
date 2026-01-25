        package lucca.shizuru.notifyhub.services.strategies;


        import lombok.extern.slf4j.Slf4j;
        import lucca.shizuru.notifyhub.domain.entities.Notification;
        import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
        import org.springframework.stereotype.Component;


        @Slf4j
        @Component
        public class EmailNotificationStrategy implements NotificationStrategy {
            @Override
            public void sendNotification(Notification notification) {
               log.info("Enviando E-mail para: {} | Mensagem: {}",
                       notification.getDestination(),
                       notification.getContent());
            }

            @Override
            public boolean isApplicable(NotificationChannel channel) {
                return NotificationChannel.EMAIL.equals(channel);
            }

            @Override
            public void validate(String destination) {
                if (destination == null || !destination.contains("@")) {
                    log.warn(" Falha na validação de e-mail: destino inválido -> {}", destination);
                    throw new IllegalArgumentException("Para E-mail, o destino deve ser um endereço de e-mail válido.");
                }
            }


        }
