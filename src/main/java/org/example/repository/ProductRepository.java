package org.example.repository;

import java.util.Optional;

import org.example.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface 
ProductRepository extends JpaRepository<Product, Long>{
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
}
