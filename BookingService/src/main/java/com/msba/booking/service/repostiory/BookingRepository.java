package com.msba.booking.service.repostiory;

import com.msba.booking.service.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {
}
