package com.msba.booking.service.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String paymentMode;
    private Integer bookingId;
    private String upiId;
    private String cardNumber;
}
