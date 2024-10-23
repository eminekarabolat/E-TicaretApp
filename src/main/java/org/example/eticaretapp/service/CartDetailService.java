package org.example.eticaretapp.service;

import org.example.eticaretapp.entity.CartDetails;
import org.example.eticaretapp.repository.CartDetailsRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService {


    private CartDetailsRepository cartDetailsRepository;
    private ShoppingCartService shoppingCartService;

    public CartDetailService(CartDetailsRepository cartDetailsRepository, @Lazy ShoppingCartService shoppingCartService) {
        this.cartDetailsRepository = cartDetailsRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public void save(CartDetails cartDetails) {
        cartDetailsRepository.save(cartDetails);
    }

    public List<Long> findProductIdListByShoppingCardId(Long shoppingCardId) {
        return cartDetailsRepository.findProductIdListByShoppingCardId(shoppingCardId);
    }
}
