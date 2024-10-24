package org.example.eticaretapp.dto.request;

import java.util.List;

public record SimpleFindProductRequestDto(
		Double minPrice,
		Double maxPrice,
		List<String> brand) {
	
}