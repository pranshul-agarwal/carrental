package com.gb.rental.service;

import com.gb.rental.model.reservation.InvoiceNotification;
import com.gb.rental.model.reservation.ReservationReminderNotification;

public interface NotificationService {
    void notifyUser(InvoiceNotification invoice);
    void notifyUser(ReservationReminderNotification reservationReminderNotification);
}
