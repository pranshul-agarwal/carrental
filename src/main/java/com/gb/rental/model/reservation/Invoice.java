package com.gb.rental.model.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Invoice {
    private String invoiceId;
    private String reservationId;
    private String userId;
    private double usageCharges;
    private double addonEquipmentsCost;
    private double addonServicesCost;
    private double taxes;
    private double total;
}
