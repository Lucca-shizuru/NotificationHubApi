package lucca.shizuru.notifyhub.dto;

import jakarta.validation.constraints.NotBlank;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import lucca.shizuru.notifyhub.domain.enums.NotificationPriority;


public record NotificationRequestDto (

        @NotBlank(message = "O conteúdo é obrigatório")
        String content,

        @NotBlank(message = "O canal é obrigatório")
        NotificationChannel channel,

        @NotBlank(message = "A prioridade é obrigatório")
        NotificationPriority priority,

        @NotBlank(message = "O destino é obrigatório")
        String destination


){
}
