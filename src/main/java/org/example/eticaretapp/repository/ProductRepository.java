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
	
	@Query("SELECT p.id FROM Product p WHERE p.brand IN ?3 AND p.price BETWEEN ?1 AND ?2")
	List<Long> findAllByPriceBetweenAndBrandIn(Double minPrice, Double maxPrice, List<String> brand);
	
	@Query("SELECT p.id FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
	List<Long> findAllByPriceBetween(Double minPrice, Double maxPrice);
	
	
}