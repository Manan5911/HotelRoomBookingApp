package com.msba.booking.service.service;

import com.msba.booking.service.dto.BookingDto;
import com.msba.booking.service.dto.PaymentDto;
import com.msba.booking.service.entity.BookingInfoEntity;

public interface BookingService {
    BookingInfoEntity createBooking(BookingDto bookingDto);
    BookingInfoEntity makePayment(PaymentDto paymentDto);
}
