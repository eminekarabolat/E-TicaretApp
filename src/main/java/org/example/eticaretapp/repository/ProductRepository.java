package org.example.eticaretapp.repository;

import org.example.eticaretapp.dto.request.SimpleFindProductRequestDto;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.entity.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findBySellerId(Long sellerId);
	
	List<Product> findBySellerIdAndState(Long sellerId, State state);
	
	List<Product> findAllByPriceBetweenAndBrandIn(Double minPrice, Double maxPrice, List<String> brand);
	
	List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
	
	
}