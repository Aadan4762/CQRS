package com.adan.productcommandservice.repository;

import com.adan.productcommandservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
