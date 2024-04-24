package org.example.service;

import org.example.data.ProductData;
import org.example.data.ResourceId;

public interface ProductService {
    ProductData getProduct(Long id);
    ResourceId saveProduct(ProductData product);
}
