package com.gb.rental.service.impl;

import com.gb.rental.model.account.User;
import com.gb.rental.model.enums.NotificationStatus;
import com.gb.rental.model.reservation.InvoiceNotification;
import com.gb.rental.model.reservation.ReservationReminderNotification;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleReservationRepository;
import com.gb.rental.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public void notifyUser(ReservationReminderNotification reservationReminderNotification) {
        VehicleReservation vehicleReservation =
                vehicleReservationRepository.getVehicleReservation(reservationReminderNotification.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        System.out.println("Notified user" + user.getContact().getEmail());
        reservationReminderNotification.setNotificationStatus(NotificationStatus.SENT);
    }

    @Override
    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap
                .get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
