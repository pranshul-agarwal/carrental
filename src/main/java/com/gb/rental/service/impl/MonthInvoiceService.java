package com.gb.rental.service.impl;

import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.reservation.cost.VehicleFixedCosts;
import com.gb.rental.model.reservation.cost.VehicleMonthlyCosts;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.repository.UserRepository;
import com.gb.rental.repository.VehicleRepository;
import com.gb.rental.service.InvoiceService;
import com.gb.rental.service.util.AddonCostUtil;

import java.time.Duration;
import java.util.UUID;

public class MonthInvoiceService implements InvoiceService {
    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusMonths(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double months = Math.ceil(days / 30);

        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());

        double monthlyCost = VehicleMonthlyCosts.
                vehicleMonthlyCost.get(hireableVehicle.getVehicleType());
        double fixedCost = VehicleFixedCosts
                .vehicleFixedCost.get(hireableVehicle.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double addonServiceCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double rentalCost = months * monthlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
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
