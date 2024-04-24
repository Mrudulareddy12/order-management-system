package org.example.controller;

import org.example.data.CustomerData;
import org.example.data.OrderRequestPayload;
import org.example.data.ResourceId;
import org.example.service.CustomerService;
import org.example.service.OrderService;
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
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;
	private OrderService orderService;
	private final Retry retry;

    @Autowired
    CustomerController(final CustomerService customerService,final RetryRegistry retryRegistry, final OrderService orderService){
        this.customerService = customerService;
		this.retry = retryRegistry.retry("myRetry");
		this.orderService = orderService; 
    }

	@GetMapping("/users/{id}")
	public CustomerData getUser(@PathVariable Long id) throws Exception {
		return retry.executeCallable(() -> customerService.getCustomer(id));
	}
	
	@PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResourceId save(@RequestBody CustomerData newUser) throws Exception {
		return retry.executeCallable(() -> customerService.saveCustomer(newUser));
	}

	@PostMapping(path = "/users/{id}/addToCart", produces = MediaType.APPLICATION_JSON_VALUE)
	public String save(@PathVariable Long id,@RequestBody OrderRequestPayload orderRequestPayload) throws Exception {
		return retry.executeCallable(() ->this.orderService.orderProduct(id,orderRequestPayload));
	}

	// @PostMapping(path = "/users/{id}/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
	// public Double placeOrder(@PathVariable Long id,@RequestBody OrderRequestPayload orderRequestPayload) throws Exception {
	// 	return retry.executeCallable(() ->this.orderService.orderProduct(id,orderRequestPayload));
	// }
}
