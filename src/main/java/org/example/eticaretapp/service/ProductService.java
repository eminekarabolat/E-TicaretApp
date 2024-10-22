package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.dto.request.DeleteProductDto;
import org.example.eticaretapp.entity.Product;
import org.example.eticaretapp.entity.enums.State;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.hibernate.validator.internal.constraintvalidators.hv.UUIDValidator;
import org.springframework.boot.actuate.autoconfigure.metrics.data.RepositoryMetricsAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final AuthService authService;
	private final JwtManager jwtManager;
	private final RepositoryMetricsAutoConfiguration repositoryMetricsAutoConfiguration;
	
	public List<Product> listMyProducts(String token) {
		Long sellerId = validateToken(token);
		//else if(authService.findById(sellerId))
		return productRepository.findBySellerIdAndState(sellerId, State.ACTIVE);
	}
	
	public void addMyProduct(AddProductDto dto) {
		Long sellerId = validateToken(dto.token());
		String sku = UUID.randomUUID().toString();
		productRepository.save(ProductMapper.INSTANCE.fromAddProductDto(dto, sellerId, sku));
	}
	
	public void deleteMyProduct(DeleteProductDto dto) {
		Long sellerId = validateToken(dto.token());
		Optional<Product> optProduct = productRepository.findById(dto.productId());
		if (optProduct.isEmpty()) throw new ETicaretException(ErrorType.NOTFOUND_PRODUCT);
		Product product = optProduct.get();
		if(!product.getSellerId().equals(sellerId)) throw new ETicaretException(ErrorType.SELLER_PRODUCT_ERROR);
		product.setState(State.DELETED);
		productRepository.save(product);
	}
	
	public Long validateToken(String token) {
		Optional<Long> sellerId = jwtManager.validateToken(token);
		if (sellerId.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
		else return sellerId.get();
	}
}