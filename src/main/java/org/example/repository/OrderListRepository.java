package org.example.repository;

import java.util.Optional;

import org.example.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository  extends JpaRepository<OrderList, Long>{
    Optional<OrderList> findById(Long id);
}
