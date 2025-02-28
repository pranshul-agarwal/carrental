package com.gb.rental.model.reservation.addon.equipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class VehicleAddon {
    private String id;
    private String name;
    private String description;
    private double cost;
}
