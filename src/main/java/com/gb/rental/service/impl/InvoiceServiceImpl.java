package com.gb.rental.service.impl;

import com.gb.rental.model.reservation.Invoice;
import com.gb.rental.model.enums.ReservationStatus;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.service.util.InvoiceBuilderUtil;
import com.gb.rental.service.InvoiceService;
import com.gb.rental.service.InvoiceServiceFactory;

public class InvoiceServiceImpl implements InvoiceService {
    InvoiceServiceFactory invoiceServiceFactory = new InvoiceServiceFactory();

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        if (vehicleReservation.getStatus() == ReservationStatus.CANCELLED)
            return InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }
}
