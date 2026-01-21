package lucca.shizuru.notifyhub.controllers;


import jakarta.validation.Valid;
import lucca.shizuru.notifyhub.domain.Notification;
import lucca.shizuru.notifyhub.dto.NotificationRequestDto;
import lucca.shizuru.notifyhub.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationRequestDto dto) {
        Notification notification = new Notification();
        notification.setContent(dto.content());
        notification.setDestination(dto.destination());
        notification.setChannel(dto.channel());
        notification.setPriority(dto.priority());

        var result = notificationService.scheduleNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
