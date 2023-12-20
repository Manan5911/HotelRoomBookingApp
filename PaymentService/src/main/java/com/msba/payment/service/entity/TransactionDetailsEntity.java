package com.msba.payment.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transcation")
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transcationId;
    @Column(nullable = false)
    private String paymentMode;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer bookingId;
    private String upiId;
    @Column(nullable = false)
    private String cardNumber;
}
