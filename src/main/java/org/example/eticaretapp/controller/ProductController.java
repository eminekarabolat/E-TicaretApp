package org.example.eticaretapp.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.dto.request.DeleteProductDto;
import org.example.eticaretapp.dto.request.UpdateProductRequestDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.entity.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.service.CloudinaryService;
import org.example.eticaretapp.service.ImageService;
import org.example.eticaretapp.service.ProductService;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

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





}