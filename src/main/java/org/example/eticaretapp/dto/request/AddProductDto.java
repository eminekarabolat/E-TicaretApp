package org.example.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.eticaretapp.entity.enums.Status;

public record AddProductDto(
		String token,
		String brand,
		String name,
		String description,
		@Positive
		Double price,
		@Positive
		Integer stockQuantity,
		Status status,
		@Positive
		Float weight,
		@NotNull
		String imageUrl
		) {
}