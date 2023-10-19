package com.adan.productqueryservice.repository;


import com.adan.productqueryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
