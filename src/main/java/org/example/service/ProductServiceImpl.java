package org.example.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.example.data.ProductData;
import org.example.data.ResourceId;
import org.example.domain.Product;
import org.example.repository.ProductRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepositoryWrapper productRepositoryWrapper;

    @Autowired
    ProductServiceImpl(final ProductRepositoryWrapper productRepositoryWrapper) {
        this.productRepositoryWrapper = productRepositoryWrapper;
    }

    @Override
    public ProductData getProduct(Long id) {
        Product productDetails = this.productRepositoryWrapper.getById(id);
        return ProductData.builder().name(productDetails.getName()).description(productDetails.getDescription())
                .price(productDetails.getPrice()).quantityAvailable(productDetails.getQuantityAvailable())
                .id(productDetails.getId()).build();

    }

    @Override
    public ResourceId saveProduct(ProductData product) {
        Product productDetails = new Product();
        productDetails.setName(product.getName());
        productDetails.setPrice(product.getPrice());
        productDetails.setQuantityAvailable(product.getQuantityAvailable());
        productDetails.setDescription(product.getDescription());
        Instant instant = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(instant);
        productDetails.setCreatedAt(currentTimestamp);
        productDetails.setUpdatedAt(currentTimestamp);
        Product savedDetails = this.productRepositoryWrapper.save(productDetails);
        return ResourceId.builder().id(savedDetails.getId()).build();
    }

    


}
