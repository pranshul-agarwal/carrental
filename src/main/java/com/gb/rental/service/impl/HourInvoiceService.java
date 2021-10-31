package com.gb.rental.service.impl;

import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.cost.VehicleFixedCosts;
import com.gb.rental.model.reservation.cost.VehicleHourlyCosts;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.service.InvoiceService;
import com.gb.rental.service.util.AddonCostUtil;

import java.time.Duration;
import java.util.UUID;

public class HourInvoiceService implements InvoiceService {

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusHours(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        if (hours == 0)
            hours = 1;

        double hourlyCost = VehicleHourlyCosts.
                vehicleHourlyCost.get(vehicleReservation.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(vehicleReservation.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double addonServiceCost = AddonCostUtil.computeServiceCost(vehicleReservation);
        double rentalCost = hours * hourlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        return Invoice.builder()
                .invoiceId(UUID.randomUUID().toString())
                .reservationId(vehicleReservation.getReservationId())
                .userId(user.getEmail())
                .addonEquipmentsCost(vehicleAddonCost)
                .addonServicesCost(addonServiceCost)
                .usageCharges(rentalCost)
                .taxes(taxes)
                .total(rentalCost + taxes)
                .build();
    }
}
