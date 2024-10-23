package org.example.eticaretapp.controller;


import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductToShoppingCartDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.example.eticaretapp.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(SHOPPING_CART)
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ResponseEntity<BaseResponse<Boolean>> addProductToShoppingCart(AddProductToShoppingCartDto dto){
        shoppingCartService.addProductToShoppingCart(dto);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "Ürün sepetinize eklendi."));
    }


}
