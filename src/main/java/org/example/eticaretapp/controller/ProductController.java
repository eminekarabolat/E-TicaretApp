package org.example.eticaretapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.eticaretapp.dto.request.*;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.service.CloudinaryService;
import org.example.eticaretapp.service.ImageService;
import org.example.eticaretapp.service.ProductService;
import org.example.eticaretapp.view.VwProductDetail;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.example.eticaretapp.constants.RestApis.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @GetMapping(LIST_MY_PRODUCTS)
    public ResponseEntity<BaseResponse<List<Product>>> listMyProducts(String token) {
        return ResponseEntity.ok(BaseResponse.getSuccess(productService.listMyProducts(token), "kendi urun listeleme basarili"));
    }


    @PostMapping(ADD_PRODUCT)
    public ResponseEntity<BaseResponse<Boolean>> addMyProduct(@RequestBody @Valid AddProductDto dto) {
        productService.addMyProduct(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "urun ekle basari"));
    }

    @PostMapping(DELETE_PRODUCT)
    public ResponseEntity<BaseResponse<Boolean>> deleteMyProduct(@RequestBody @Valid DeleteProductDto dto) {
        productService.deleteMyProduct(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "urun sil basari"));

    }

    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<BaseResponse<Boolean>> updateMyProduct(@RequestBody @Valid UpdateProductRequestDto dto) {
        productService.updateMyProduct(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "urun güncelleme başarılı."));
    }
    
    @PostMapping(FILTER)
    public ResponseEntity<BaseResponse<List<VwProducts>>> findProducts(
            @RequestBody @Valid FindProductRequestDto dto) {
       
        return ResponseEntity.ok(BaseResponse.getSuccess(
                productService.findProducts(dto),"Ürünler başarıyla getirildi."));
    
    }

    @PostMapping("/product-detail")
    public ResponseEntity<BaseResponse<VwProductDetail>> findProductDetail(@RequestBody @Valid FindProductDetailRequestDto dto){
        return ResponseEntity.ok(BaseResponse.getSuccess(
                productService.findProductDetails(dto),"Ürün detayı başarı ile getirildi."));
    }

}