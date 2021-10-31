package com.gb.rental.service.impl;

import com.gb.rental.model.vehicle.HireableVehicle;
import com.gb.rental.model.enums.VehicleType;
import com.gb.rental.service.VehicleMatchingStrategy;
import com.gb.rental.service.VehicleSearchService;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleSearchServiceImpl implements VehicleSearchService {
    VehicleMatchingStrategy vehicleMatchingStrategy = new DefaultVehicleMatchingStrategyImpl();

    @Override
    public List<HireableVehicle> search(VehicleType vehicleType, String city,
                                        LocalDateTime fromDate, LocalDateTime toDate) {
        return vehicleMatchingStrategy.getHireableVehicles(vehicleType, city, fromDate, toDate);
    }

    @Override
    public List<HireableVehicle> search(String make, String model, String city,
                                        LocalDateTime fromDate, LocalDateTime toDate) {
        return vehicleMatchingStrategy.search(make, model, city, fromDate, toDate);
    }

    @Override
    public List<HireableVehicle> search(int seats, String city,
                                        LocalDateTime fromDate, LocalDateTime toDate) {
        return vehicleMatchingStrategy.search(seats, city, fromDate, toDate);
    }
}
