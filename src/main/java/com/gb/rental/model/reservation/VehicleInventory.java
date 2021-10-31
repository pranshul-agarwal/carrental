package com.gb.rental.model.reservation;

import com.gb.rental.model.enums.ReservationStatus;
import com.gb.rental.model.enums.VehicleReservationType;
import com.gb.rental.model.vehicle.HireableVehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class VehicleInventory {
    private String inventoryId;
    //private VehicleReservation vehicleReservation;
    private String reservationId;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private double startMileage;
    private double endMileage;
    private HireableVehicle vehicle;
    private VehicleReservationType vehicleReservationType;

    public VehicleInventory() {
    }

    public VehicleInventory(VehicleReservation vehicleReservation,
                            HireableVehicle hireableVehicle) {
        this.inventoryId = UUID.randomUUID().toString();
        //this.vehicleReservation = vehicleReservation;
        this.reservationId = vehicleReservation.getReservationId();
        this.reservationDate = vehicleReservation.getReservationDate();
        this.status = vehicleReservation.getStatus();
        this.fromDate = vehicleReservation.getFromDate();
        this.dueDate = vehicleReservation.getDueDate();
        this.returnDate = vehicleReservation.getReturnDate();
        this.startMileage = vehicleReservation.getStartMileage();
        this.endMileage = vehicleReservation.getEndMileage();
        this.vehicle = hireableVehicle;
        this.vehicleReservationType = vehicleReservation.getVehicleReservationType();
    }

    public VehicleInventory(HireableVehicle hireableVehicle) {
        this.inventoryId = UUID.randomUUID().toString();
        this.startMileage = hireableVehicle.getMileage();
        this.endMileage = hireableVehicle.getMileage();
        this.vehicle = hireableVehicle;
    }
}
