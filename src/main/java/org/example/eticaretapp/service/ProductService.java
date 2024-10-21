package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.entity.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.hibernate.validator.internal.constraintvalidators.hv.UUIDValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final JwtManager jwtManager;
	
	public List<Product> listMyProducts(String token) {
		Optional<Long> sellerId = jwtManager.validateToken(token);
		if (sellerId.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
		return productRepository.findBySellerId(sellerId.get());
	}
	
	public void addMyProduct(AddProductDto dto) {
		Optional<Long> sellerId = jwtManager.validateToken(dto.token());
		if (sellerId.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
		String sku = UUID.randomUUID().toString();
		productRepository.save(ProductMapper.INSTANCE.fromAddProductDto(dto, sellerId.get(), sku));
	}
}