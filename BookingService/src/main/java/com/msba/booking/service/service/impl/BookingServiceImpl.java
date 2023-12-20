package com.msba.booking.service.service.impl;

import com.msba.booking.service.dto.BookingDto;
import com.msba.booking.service.dto.PaymentDto;
import com.msba.booking.service.entity.BookingInfoEntity;
import com.msba.booking.service.exception.InvalidPaymentModeException;
import com.msba.booking.service.exception.NoSuchBookingIdExistsException;
import com.msba.booking.service.repostiory.BookingRepository;
import com.msba.booking.service.service.BookingService;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private RestTemplate restTemplate;

    public BookingServiceImpl(BookingRepository bookingRepository, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public BookingInfoEntity createBooking(BookingDto bookingDto) {
        BookingInfoEntity entity = new BookingInfoEntity();
        entity.setFromDate(bookingDto.getFromDate());
        entity.setToDate(bookingDto.getToDate());
        entity.setAadharNumber(bookingDto.getAadharNumber());
//        LocalDate date1 = LocalDate.parse(bookingDto.getFromDate().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate date2 = LocalDate.parse(bookingDto.getToDate().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
//        long days = ChronoUnit.DAYS.between(date1, date2);
//        LocalDate localDate1 = LocalDate.parse(entity.getFromDate().toString());
//        LocalDate localDate2 = LocalDate.parse(entity.getToDate().toString());
//        Period intervalPeriod = Period.between(localDate1, localDate2);
//        long daysDiff = 0;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date1 = sdf.parse(entity.getFromDate().toString());
//            Date date2 = sdf.parse(entity.getToDate().toString());
//            long timeDiff = Math.abs(date2.getTime() - date1.getTime());
//            daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int days = 5;

        Random rand = new Random();
        int num1 = rand.nextInt(100) + 1;
        int num2 = rand.nextInt(100) + 1;
        int num3 = rand.nextInt(100) + 1;
        String rooms = Integer.toString(num1) + " " + Integer.toString(num2) + " " + Integer.toString(num3);
        entity.setRoomNumbers(rooms);
        entity.setNumOfRooms(bookingDto.getNumOfRooms());
        int roomPrice = 1000 * bookingDto.getNumOfRooms() * days;
        entity.setRoomPrice(roomPrice);
        entity.setTransactionId(0);
        LocalDate local = LocalDate.now();
        Date date4 = java.sql.Date.valueOf(local);
        entity.setBookedOn(date4);

        return bookingRepository.save(entity);
    }

    @Override
    public BookingInfoEntity makePayment(PaymentDto paymentDto) {
        if(paymentDto.getPaymentMode().equals("UPI") || paymentDto.getPaymentMode().equals("CARD")){
            BookingInfoEntity sample = bookingRepository.findById(paymentDto.getBookingId()).orElseThrow(() -> new NoSuchBookingIdExistsException("Invalid Booking ID"));
            BookingInfoEntity entity = bookingRepository.findById(paymentDto.getBookingId()).get();
            ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("http://localhost:8083/payment/transaction", paymentDto, Integer.class);
            Integer id = responseEntity.getBody();
            entity.setTransactionId(id);
            BookingInfoEntity created =  bookingRepository.save(entity);
            System.out.println("Booking confirmed for user with aadhar number:" + entity.getAadharNumber() + " | " + "Here are the booking details: "+entity.toString());
            return created;
        }
        else{
            throw new InvalidPaymentModeException("Invalid mode of payment!");
        }
    }
}
