package org.example.repository;

import java.util.Optional;

import org.example.domain.Product;
import org.example.exception.ProductAlreadyExistsException;
import org.example.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRepositoryWrapper {

    private ProductRepository productRepository;

    @Autowired
    ProductRepositoryWrapper(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("null")
    public Product save(Product product) {
        Optional<Product> productDetails = this.productRepository.findByName(product.getName());
        if (productDetails.isEmpty() || productDetails.get() == null) {
            return this.productRepository.save(product);
        } else {
            throw new ProductAlreadyExistsException(product.getName());
        }
    }

    public Product getById(Long id) {
        // check by name
        @SuppressWarnings("null")
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(id);
    }

    public Product update(Long productId, Integer availableQuantity) {
        Product productDetails = this.getById(productId);
        if (productDetails != null) {
            productDetails.setQuantityAvailable(availableQuantity);
            return this.productRepository.save(productDetails);
        }
        return null;
    }

}
