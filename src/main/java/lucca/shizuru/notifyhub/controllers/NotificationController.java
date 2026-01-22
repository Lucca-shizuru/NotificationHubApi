package lucca.shizuru.notifyhub.controllers;


import jakarta.validation.Valid;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.dto.NotificationRequestDto;
import lucca.shizuru.notifyhub.factory.NotificationFactory;
import lucca.shizuru.notifyhub.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationFactory notificationFactory;

    public NotificationController(NotificationService notificationService) {

        this.notificationService = notificationService;
        this.notificationFactory = new NotificationFactory();
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody @Valid NotificationRequestDto dto) {
        Notification notification = notificationFactory.createNotification(dto);

        var result = notificationService.scheduleNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
