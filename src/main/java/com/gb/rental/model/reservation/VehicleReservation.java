package com.gb.rental.model.reservation;

import com.gb.rental.model.common.Address;
import com.gb.rental.model.enums.ReservationStatus;
import com.gb.rental.model.enums.VehicleReservationType;
import com.gb.rental.model.enums.VehicleType;
import com.gb.rental.model.reservation.addon.equipment.VehicleAddon;
import com.gb.rental.model.reservation.addon.service.AddonService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VehicleReservation {
    private String reservationId;
    private String usrId;
    private LocalDateTime reservationDate;
    private ReservationStatus status;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private Address pickupLocation;
    private Address dropLocation;
    private double startMileage; // is this needed
    private double endMileage; // is this needed
    private String accocatedVehicleId;
    private VehicleType vehicleType;
    private String invoiceId;
    private List<VehicleAddon> vehicleAddons;
    private List<AddonService> addonServices;
    private VehicleReservationType vehicleReservationType;
}
