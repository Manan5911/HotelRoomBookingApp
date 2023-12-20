package com.msba.booking.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookingDto {
    private Date fromDate;
    private Date toDate;
    private String aadharNumber;
    private Integer numOfRooms;
}
