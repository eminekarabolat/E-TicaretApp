package org.example.eticaretapp.dto.request;

public record AddImageMyProductRequestDto(
		String token,
		Long productId,
		String url,
		String title
) {
}