package org.example.repository;

import java.util.List;

import org.example.domain.OrderList;
import org.springframework.stereotype.Service;

@Service
public class OrderListRepositoryWrapper {

    private OrderListRepository orderListRepository;
    
    OrderListRepositoryWrapper(final OrderListRepository orderListRepository){
        this.orderListRepository = orderListRepository;
    }

    public OrderList save(OrderList orderList){
        return this.orderListRepository.save(orderList);
    }

    public List<OrderList> saveAll(List<OrderList> orderList){
        return this.orderListRepository.saveAll(orderList);
    }


}
