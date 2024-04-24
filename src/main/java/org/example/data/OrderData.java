package org.example.data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderData {
    private Long id;
    private Long customerId;
    private Timestamp orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderListData> items;
}
