package com.gb.rental.model.reservation;

import com.gb.rental.model.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationReminderNotification {
    private LocalDateTime reservationDate;
    private String reservationId;
    private String userId;
    private LocalDateTime createdDate;
    private NotificationStatus notificationStatus;
}
