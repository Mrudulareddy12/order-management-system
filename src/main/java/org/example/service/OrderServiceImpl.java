package org.example.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.example.data.OrderRequestPayload;
import org.example.data.OrderStatus;
import org.example.data.PaymentStatus;
import org.example.domain.Order;
import org.example.domain.OrderList;
import org.example.domain.Payment;
import org.example.domain.Product;
import org.example.exception.StockNotAvailableException;
import org.example.repository.CustomerRepositoryWrapper;
import org.example.repository.OrderListRepositoryWrapper;
import org.example.repository.OrderRepositoryWrapper;
import org.example.repository.PaymentRepositoryWrapper;
import org.example.repository.ProductRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepositoryWrapper orderRepositoryWrapper;
    private OrderListRepositoryWrapper orderListRepositoryWrapper;
    private ProductRepositoryWrapper productRepositoryWrapper;
    private CustomerRepositoryWrapper customerRepositoryWrapper;
    private PaymentRepositoryWrapper paymentRepositoryWrapper;

    @Autowired
    OrderServiceImpl(final OrderRepositoryWrapper orderRepositoryWrapper,
            final OrderListRepositoryWrapper orderListRepositoryWrapper,
            final ProductRepositoryWrapper productRepositoryWrapper,
            final CustomerRepositoryWrapper customerRepositoryWrapper,
            PaymentRepositoryWrapper paymentRepositoryWrapper) {
        this.orderRepositoryWrapper = orderRepositoryWrapper;
        this.orderListRepositoryWrapper = orderListRepositoryWrapper;
        this.productRepositoryWrapper = productRepositoryWrapper;
        this.customerRepositoryWrapper = customerRepositoryWrapper;
        this.paymentRepositoryWrapper = paymentRepositoryWrapper;
    }

    @Override
    public String orderProduct(Long userId, OrderRequestPayload payload) {

        //check the stock is available or not 
        this.customerRepositoryWrapper.getById(userId);
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING.toString());
        order.setCustomerId(userId);
        Instant instant = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(instant);
        order.setCreatedAt(currentTimestamp);
        order.setUpdatedAt(currentTimestamp);
        Order orderDetails = this.orderRepositoryWrapper.save(order);
        //logging the order details
        List<OrderList> listOfOrders = new ArrayList<>();
        double totalAmount = payload.getItems().stream()
        .mapToDouble(orderData -> {
            Product productData = this.productRepositoryWrapper.getById(orderData.getProductId());
            OrderList list = new OrderList();
            list.setOrderId(orderDetails.getId());
            list.setProductId(orderData.getProductId());
            if (orderData.getQuantity() >= productData.getQuantityAvailable()) {
                throw new StockNotAvailableException(productData.getName());
            }
            this.productRepositoryWrapper.update(productData.getId(),
                    productData.getQuantityAvailable() - orderData.getQuantity());
            list.setQuantity(orderData.getQuantity());
            double totalPrice = productData.getPrice() * orderData.getQuantity();
            list.setTotalPrice(totalPrice);
            list.setUnitPrice(productData.getPrice());
            listOfOrders.add(list);
            return totalPrice;
        })
        .sum();
        this.orderListRepositoryWrapper.saveAll(listOfOrders);
        orderDetails.setTotalAmount(totalAmount);
        orderDetails.setStatus(OrderStatus.COMPLETED.toString());
        this.orderRepositoryWrapper.update(orderDetails.getId(), orderDetails);
        Payment paymentDetails = new Payment();
        paymentDetails.setAmount(totalAmount);
        paymentDetails.setOrderId(orderDetails.getId());
        paymentDetails.setPaymentMode(payload.getPaymentMode());
        paymentDetails.setPaymentStatus(PaymentStatus.PAID.name());
        paymentDetails.setPaymentDate(currentTimestamp);
        this.paymentRepositoryWrapper.save(paymentDetails);
        return "Order Placed Successfully";
    }

}
