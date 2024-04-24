package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class OrderListData {
    OrderListData(){
        
    }

    private Long productId;
    private int quantity;
}
