package org.example.eticaretapp.service;

import org.example.eticaretapp.dto.request.AddProductToShoppingCartDto;
import org.example.eticaretapp.entity.CartDetails;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
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
    
    public Integer removeItem(Long shoppingCartId, Long productId, Integer quantityToBeRemoved){
        CartDetails cartDetail =
                cartDetailsRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId);
        if (cartDetail == null) throw new ETicaretException(ErrorType.ITEM_NOT_IN_CART);
        
        Integer presentQuantity = cartDetail.getQuantity();
        if (presentQuantity <= quantityToBeRemoved) {
            cartDetailsRepository.delete(cartDetail);
            return presentQuantity;
        }
        else {
            cartDetail.setQuantity(presentQuantity - quantityToBeRemoved);
            cartDetailsRepository.save(cartDetail);
            return quantityToBeRemoved;
        }
    }
}