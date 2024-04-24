package org.example.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @Column(name = "payment_status")
    private String paymentStatus;
}
