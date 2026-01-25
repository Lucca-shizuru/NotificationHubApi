package lucca.shizuru.notifyhub.controllers;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.dto.NotificationRequestDto;
import lucca.shizuru.notifyhub.factory.NotificationFactory;
import lucca.shizuru.notifyhub.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody @Valid NotificationRequestDto dto) {
        log.info("Recebida requisição para canal: {} com prioridade: {}", dto.channel(), dto.priority());

        var result = notificationService.scheduleNotification(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> ListAllNotifications() {
        log.info("Retornando todos os notificacoes.");
        List<Notification> list = notificationService.GetAllNotifications();

        return ResponseEntity.ok(list);
    }
}
