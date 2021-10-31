package com.gb.rental.service;

import com.gb.rental.model.enums.VehicleType;
import com.gb.rental.model.vehicle.HireableVehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleMatchingStrategy {
    List<HireableVehicle> getHireableVehicles(VehicleType vehicleType, String city,
                                              LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(String make, String model, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(int seats, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);
}
