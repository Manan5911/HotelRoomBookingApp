package com.msba.payment.service.service;

import com.msba.payment.service.dto.PaymentDto;
import com.msba.payment.service.entity.TransactionDetailsEntity;

public interface PaymentService {
    Integer createTranscation(PaymentDto paymentDto);
    TransactionDetailsEntity getTransactionById(Integer id);
}
