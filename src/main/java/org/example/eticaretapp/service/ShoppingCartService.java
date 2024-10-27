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
        Optional<Long> userIdOpt = jwtManager.validateToken(dto.token());
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);

        Optional<Product> productOptional = productService.findById(dto.productId());
        if(productOptional.isEmpty()) throw new ETicaretException(ErrorType.NOTFOUND_PRODUCT);

        Product product = productOptional.get();
        ShoppingCart shoppingCart = getCurrentCart(userIdOpt.get());
        
        CartDetails cartDetails = CartDetails.builder()
                .shoppingCartId(shoppingCart.getId())
                .productId(product.getId())
                .price(product.getPrice())
                .quantity(dto.quantity())
                .build();
        cartDetailService.save(cartDetails);
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + product.getPrice()* dto.quantity());
        shoppingCartRepository.save(shoppingCart);
    }

    public List<Product> getProductsInMyCart(String token) {
        List<Product> productList = new ArrayList<>();

        Optional<Long> userIdOpt = jwtManager.validateToken(token);
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
        
        ShoppingCart shoppingCart
                = getCurrentCart(userIdOpt.get());
       
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
    
    private ShoppingCart getCurrentCart(Long userId){
        List<ShoppingCart> resultList
                = shoppingCartRepository.findByUserIdAndIsDone(userId, false);
        if (resultList.isEmpty()) {
            ShoppingCart newCart = ShoppingCart.builder().userId(userId).build();
            return shoppingCartRepository.save(newCart);
        }
        else return resultList.get(0);
    }
    
    public void deleteProductFromShoppingCart(AddProductToShoppingCartDto dto) {
        Optional<Long> userIdOpt = jwtManager.validateToken(dto.token());
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
        
        Optional<Product> productOptional = productService.findById(dto.productId());
        if(productOptional.isEmpty()) throw new ETicaretException(ErrorType.NOTFOUND_PRODUCT);
        
        Product product = productOptional.get();
        ShoppingCart shoppingCart = getCurrentCart(userIdOpt.get());
        
        Integer removedQuantity = cartDetailService.removeItem(
                shoppingCart.getId(), product.getId(), dto.quantity());
        
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - product.getPrice() * removedQuantity);
        shoppingCartRepository.save(shoppingCart);
    }
}