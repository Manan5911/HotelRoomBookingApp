package com.msba.payment.service.repository;

import com.msba.payment.service.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TransactionDetailsEntity, Integer> {
}
