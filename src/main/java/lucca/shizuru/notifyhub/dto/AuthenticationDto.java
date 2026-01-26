package lucca.shizuru.notifyhub.dto;

import lucca.shizuru.notifyhub.domain.enums.UserRole;

public record AuthenticationDto(String login, String password, UserRole role) {
}
