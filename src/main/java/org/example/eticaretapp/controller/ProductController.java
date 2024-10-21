package org.example.eticaretapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.Product;
import org.example.eticaretapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.eticaretapp.constants.RestApis.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping(LIST_MY_PRODUCTS)
	public ResponseEntity<BaseResponse<List<Product>>> listMyProducts(String token){
		return ResponseEntity.ok(BaseResponse.getSuccess(productService.listMyProducts(token), "kendi urun listeleme basarili"));
	}
	
	@PostMapping(ADD_PRODUCT)
	public ResponseEntity<BaseResponse<Boolean>> addMyProduct(@RequestBody @Valid AddProductDto dto){
		productService.addMyProduct(dto);
		return ResponseEntity.ok(BaseResponse.getSuccess(true, "urun ekle basari"));
	}
}