package org.example.eticaretapp.dto.request;

import org.example.eticaretapp.entity.enums.Status;

public record UpdateProductRequestDto(
		String token,
		Long productId,
		String name,
		String description,
		Double price,
		Integer stockQuantity,
		Status status
) {
}