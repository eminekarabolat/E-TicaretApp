package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddProductToShoppingCartDto;
import org.example.eticaretapp.entity.CartDetails;
import org.example.eticaretapp.entity.ShoppingCart;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.repository.ShoppingCartRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private JwtManager jwtManager;


    public void addProductToShoppingCart(AddProductToShoppingCartDto dto) {
        Optional<Long> userIdOpt = jwtManager.validateToken(dto.token());
        if(userIdOpt.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);

        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUserIdAndDone(userIdOpt.get(), false);
        if(shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();

            CartDetails cartDetails = CartDetails.builder()
                    .build()
        }
        else{
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .userId(userIdOpt.get())
                    .build();
            shoppingCartRepository.save(shoppingCart);
        }
    }
}
