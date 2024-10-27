package org.example.eticaretapp.controller;


import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductToShoppingCartDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.ShoppingCart;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.eticaretapp.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(SHOPPING_CART)
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping(ADDPRODUCT_TO_CART)
    public ResponseEntity<BaseResponse<Boolean>> addProductToShoppingCart(AddProductToShoppingCartDto dto){
        shoppingCartService.addProductToShoppingCart(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "Ürün sepetinize eklendi."));
    }

    @GetMapping(LIST_MY_PRODUCTS)
    public ResponseEntity<BaseResponse<List<Product>>> showMyCart(String token){
        List<Product> myProductsInCart = shoppingCartService.getProductsInMyCart(token);
        return ResponseEntity.ok(BaseResponse.getSuccess(myProductsInCart, "Sepetteki ürünler"));
    }

    @PostMapping(LIST_OLD_CARTS)
    public ResponseEntity<BaseResponse<List<ShoppingCart>>> showOldCarts(String token){
        return ResponseEntity.ok(
                BaseResponse.getSuccess(shoppingCartService.showOldCarts(token),
                                        "Eski sepetler getirildi"));
        
    }
    
    @PostMapping(ADDPRODUCT_TO_CART)
    public ResponseEntity<BaseResponse<Boolean>> deleteProductFromShoppingCart(AddProductToShoppingCartDto dto){
        shoppingCartService.deleteProductFromShoppingCart(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "Ürün sepetinizden silindi"));
    }
    
}