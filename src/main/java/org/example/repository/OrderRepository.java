package org.example.repository;

import java.util.Optional;

import org.example.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Optional<Order> findById(Long id);
}