package org.example.eticaretapp.dto.request;

import org.example.eticaretapp.entity.enums.PaymentMethod;

public record FinishShoppingDto(
		String token,
		PaymentMethod paymentMethod
) {
}