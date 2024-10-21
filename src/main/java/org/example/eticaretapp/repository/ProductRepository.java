package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findBySellerId(Long sellerId);
}