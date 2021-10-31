package com.gb.rental.service.util;

import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.cost.VehicleFixedCosts;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleRepository;

import java.util.UUID;

public class InvoiceBuilderUtil {
    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        return Invoice.builder()
                .invoiceId(UUID.randomUUID().toString())
                .reservationId(vehicleReservation.getReservationId())
                .userId(user.getEmail())
                .usageCharges(fixedCost)
                .taxes(taxes)
                .total(fixedCost + taxes)
                .build();
    }
}
