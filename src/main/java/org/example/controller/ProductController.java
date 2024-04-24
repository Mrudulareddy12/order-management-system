package org.example.controller;

import org.example.data.ProductData;
import org.example.data.ResourceId;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private ProductService productService;
	private final Retry retry;

    @Autowired
    ProductController(final ProductService productService,final RetryRegistry retryRegistry){
        this.productService = productService;
		this.retry = retryRegistry.retry("myRetry");
    }

    @GetMapping("/products/{id}")
	ProductData getProduct(@PathVariable Long id) throws Exception {
		return retry.executeCallable(() ->this.productService.getProduct(id));
	}
	
	@PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResourceId save(@RequestBody ProductData productData) throws Exception {
		return retry.executeCallable(() -> this.productService.saveProduct(productData));
	}
}
