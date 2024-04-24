package org.example.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class OrderRequestPayload {
    OrderRequestPayload(){

    }
    private List<OrderListData> items;
    private String paymentMode;
}
