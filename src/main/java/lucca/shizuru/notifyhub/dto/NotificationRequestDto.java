package lucca.shizuru.notifyhub.dto;

import jakarta.validation.constraints.NotBlank;


public record NotificationRequestDto (
        @NotBlank String content,
        @NotBlank String channel,
        @NotBlank String priority,
        @NotBlank String destination


){
}
