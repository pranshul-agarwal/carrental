package com.gb.rental.service.impl;

import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.enums.VehicleType;
import com.gb.rental.repository.VehicleInventoryRepository;
import com.gb.rental.repository.VehicleReservationRepository;
import com.gb.rental.service.VehicleReservationService;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleReservationServiceImpl implements VehicleReservationService {
    VehicleReservationRepository vehicleReservationRepository = new
            VehicleReservationRepository();

    @Override
    public List<VehicleReservation> getReservations(VehicleType vehicleType) {
        return vehicleReservationRepository.getVehicleReservations(vehicleType);
    }

    @Override
    public boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleInventoryRepository.vehicleInventoryList
                .stream().anyMatch(vehicleInventory ->
                        vehicleInventory.getVehicle().getId().equalsIgnoreCase(qrCode) &&
                                ((vehicleInventory.getDueDate() != null &&
                                        fromDate.isBefore(vehicleInventory.getDueDate()))
                                        && (vehicleInventory.getFromDate() != null
                                        && toDate.isAfter(vehicleInventory.getFromDate()))));
    }
}
