package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductToShoppingCartDto;
import org.example.eticaretapp.entity.CartDetails;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.entity.ShoppingCart;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.repository.ShoppingCartRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartDetailService cartDetailService;
    private final JwtManager jwtManager;
    private final ProductService productService;

// TODO el at
    public void addProductToShoppingCart(AddProductToShoppingCartDto dto) {
        ShoppingCart shoppingCart;
        Optional<Long> userIdOpt = jwtManager.validateToken(dto.token());
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);

        Optional<Product> productOptional = productService.findById(dto.productId());
        if(productOptional.isEmpty()) throw new ETicaretException(ErrorType.NOTFOUND_PRODUCT);

        Product product = productOptional.get();

        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUserIdAndIsDone(userIdOpt.get(), false);
        if(shoppingCartOptional.isPresent()) {
            shoppingCart = shoppingCartOptional.get();

            CartDetails cartDetails = CartDetails.builder()
                    .shoppingCartId(shoppingCart.getId())
                    .productId(product.getId())
                    .price(product.getPrice())
                    .quantity(dto.quantity())
                    .build();
            cartDetailService.save(cartDetails);
        }
        else{
            shoppingCart = ShoppingCart.builder()
                    .userId(userIdOpt.get())
                    .build();
            shoppingCartRepository.save(shoppingCart);

            CartDetails cartDetails = CartDetails.builder().shoppingCartId(shoppingCart.getId()).productId(product.getId())
                    .price(product.getPrice())
                    .quantity(dto.quantity())
                    .build();

            cartDetailService.save(cartDetails);

        }
    }

    public List<Product> getProductsInMyCart(String token) {
        List<Product> productList = new ArrayList<>();

        Optional<Long> userIdOpt = jwtManager.validateToken(token);
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
        
        ShoppingCart shoppingCart
                = shoppingCartRepository.findByUserIdAndIsDone(userIdOpt.get(), false).get(0);
       
        List<Long> productIds = cartDetailService.findProductIdListByShoppingCardId(shoppingCart.getId());
        for(Long productId : productIds) {
            Product product = productService.findById(productId).get();
            productList.add(product);
        }
        return productList;
    }
    
    public List<ShoppingCart> showOldCarts(String token){
        Optional<Long> userIdOpt = jwtManager.validateToken(token);
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
        
        return shoppingCartRepository.findByUserIdAndIsDone(userIdOpt.get(), true);
    }
}