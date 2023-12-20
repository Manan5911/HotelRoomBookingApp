package com.msba.payment.service.controller;

import com.msba.payment.service.dto.PaymentDto;
import com.msba.payment.service.entity.TransactionDetailsEntity;
import com.msba.payment.service.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Integer> createTransaction(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(paymentService.createTranscation(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping("/transaction/{transcationId}")
    public ResponseEntity<TransactionDetailsEntity> getTranscationById(@PathVariable(name = "transcationId") Integer id){
        return new ResponseEntity<>(paymentService.getTransactionById(id), HttpStatus.OK);
    }
}
