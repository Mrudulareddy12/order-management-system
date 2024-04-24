package org.example.repository;

import java.util.List;
import java.util.Optional;

import org.example.domain.Order;
import org.example.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRepositoryWrapper{

    private OrderRepository orderRepository;

    @Autowired
    OrderRepositoryWrapper(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @SuppressWarnings("null")
    public Order save(Order order){
        return this.orderRepository.save(order);
    }


    public Order getById(Long id){
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }
        throw new OrderNotFoundException(id);
    }

    public List<Order> saveAll(List<Order> orderList){
        return this.orderRepository.saveAll(orderList);
    }

    public Order update(Long orderId, Order orderList){
        Optional<Order> orderData = this.orderRepository.findById(orderId);
        if(orderData.get() != null){
            Order orderDetails = orderData.get();
            orderDetails.setTotalAmount(orderList.getTotalAmount());
            orderDetails.setStatus(orderList.getStatus());
            this.orderRepository.save(orderDetails);
        }
        return null;
    }


    public Order updateOrderAmount(Long orderId, Double totalAmount){
        Optional<Order> orderData = this.orderRepository.findById(orderId);
        if(orderData.get() != null){
            Order orderDetails = orderData.get();
            orderDetails.setTotalAmount(totalAmount);
            this.orderRepository.save(orderDetails);
        }
        return null;
    }


    
}
