package com.hcl.wallet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notifications {

    @Id
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id")
    private Transactions transaction;

    @Column(name = "recipient_type", nullable = false, length = 20)
    private String recipientType;

    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
