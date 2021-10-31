package com.gb.rental.service;

import com.gb.rental.model.enums.VehicleReservationType;
import com.gb.rental.service.impl.DayInvoiceService;
import com.gb.rental.service.impl.HourInvoiceService;
import com.gb.rental.service.impl.MonthInvoiceService;
import com.gb.rental.service.impl.PackageInvoiceServiceImpl;

public class InvoiceServiceFactory {
    public InvoiceService getInvoiceService(VehicleReservationType vehicleReservationType) {
        switch (vehicleReservationType) {
            case FOUR_HOURS:
            case EIGHT_HOURS:
                return new PackageInvoiceServiceImpl();
            case DAY:
                return new DayInvoiceService();
            case MONTH:
                return new MonthInvoiceService();
            case HOURLY:
            default:
                return new HourInvoiceService();
        }
    }
}
