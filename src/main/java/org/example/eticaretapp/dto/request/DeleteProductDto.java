package org.example.eticaretapp.dto.request;

public record DeleteProductDto(
		String token,
		Long productId
) {
}