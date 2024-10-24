package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.*;
import org.example.eticaretapp.entity.products.Computer;
import org.example.eticaretapp.entity.products.PetProduct;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.entity.enums.State;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final JwtManager jwtManager;
	private final ImageService imageService;
	
	private final ComputerService computerService;
	private final PetProductService petProductService;
	private final FashionService fashionService;
	
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
	
	public void updateMyProduct(UpdateProductRequestDto dto){
		Long sellerId = validateToken(dto.token());
		Optional<Product> optProduct = productRepository.findById(dto.productId());
		if (optProduct.isEmpty()) throw new ETicaretException(ErrorType.NOTFOUND_PRODUCT);
		Product product = optProduct.get();
		if(!product.getSellerId().equals(sellerId)) throw new ETicaretException(ErrorType.SELLER_PRODUCT_ERROR);
		 product.setName(dto.name());
		 product.setDescription(dto.description());
		 product.setPrice(dto.price());
		 product.setStockQuantity(dto.stockQuantity());
		 product.setStatus(dto.status());
		productRepository.save(product);
	}

	
	public Long validateToken(String token) {
		Optional<Long> sellerId = jwtManager.validateToken(token);
		if (sellerId.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
		else return sellerId.get();
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	//Belirtilen ürün tipinde belirtilmiş olan filtreli ürünler
	public List<VwProducts> findProducts(FindProductRequestDto dto) {
		SimpleFindProductRequestDto simpleDto = ProductMapper.INSTANCE.fromGeneralToSimpleDto(dto);
		List<Product> productList = filterProductsByGeneralProperty(simpleDto);
		
		if(dto.clazz() == Computer.class)
			return computerService.findComputers(
					ProductMapper.INSTANCE.fromGeneralToComputerDto(dto), productList);
		
		return null; //TODO hallet
	}
	
	//Bütün ürünlerde olan özelliklere göre filtreleme
	public List<Product> filterProductsByGeneralProperty(SimpleFindProductRequestDto dto){
		if (!dto.brand().isEmpty())
			return productRepository.findAllByPriceBetweenAndBrandIn(dto.minPrice(), dto.maxPrice(), dto.brand());
		else
			return productRepository.findAllByPriceBetween(dto.minPrice(), dto.maxPrice());
	}
}