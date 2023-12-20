package com.msba.booking.service.controller;

import com.msba.booking.service.dto.BookingDto;
import com.msba.booking.service.dto.PaymentDto;
import com.msba.booking.service.entity.BookingInfoEntity;
import com.msba.booking.service.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingDto bookingDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingDto), HttpStatus.CREATED);
    }

    @PostMapping("/booking/transaction")
    public ResponseEntity<BookingInfoEntity> makePayment(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(bookingService.makePayment(paymentDto), HttpStatus.CREATED);
    }
}
