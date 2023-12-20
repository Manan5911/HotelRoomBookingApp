package com.msba.payment.service.service.impl;

import com.msba.payment.service.dto.PaymentDto;
import com.msba.payment.service.entity.TransactionDetailsEntity;
import com.msba.payment.service.repository.PaymentRepository;
import com.msba.payment.service.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Integer createTranscation(PaymentDto paymentDto) {
        TransactionDetailsEntity entity = new TransactionDetailsEntity();
        entity.setPaymentMode(paymentDto.getPaymentMode());
        entity.setBookingId(paymentDto.getBookingId());
        entity.setCardNumber(paymentDto.getCardNumber());
        entity.setUpiId(paymentDto.getUpiId());
        TransactionDetailsEntity created = paymentRepository.save(entity);
        return created.getTranscationId();
    }

    @Override
    public TransactionDetailsEntity getTransactionById(Integer id) {
        TransactionDetailsEntity entity = paymentRepository.findById(id).get();
        return entity;
    }
}
