package org.example.eticaretapp.dto.request;

public record AddProductToShoppingCartDto(

        String token,
        Long productId,
        Integer quantity




) {
}
