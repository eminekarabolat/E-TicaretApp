package org.example.eticaretapp.dto.request;

public record FindProductDetailRequestDto(
        String clazz,
        String token,
        Long productId
) {
}
