package org.example.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductData {
    
    private Long id;

    private String name;

    private String description;

    private Double price;

    private int quantityAvailable;
}
