package lucca.shizuru.notifyhub.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lucca.shizuru.notifyhub.domain.enums.NotificationChannel;
import lucca.shizuru.notifyhub.domain.enums.NotificationPriority;
import lucca.shizuru.notifyhub.domain.enums.NotificationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notification {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationChannel channel;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if(this.status == null){
            this.status = NotificationStatus.PENDING;

        }
    }

}
