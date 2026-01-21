package lucca.shizuru.notifyhub.dto;

import jakarta.validation.constraints.NotBlank;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import lucca.shizuru.notifyhub.domain.enums.NotificationPriority;


public record NotificationRequestDto (
        @NotBlank String content,
        @NotBlank NotificationChannel channel,
        @NotBlank NotificationPriority priority,
        @NotBlank String destination


){
}
