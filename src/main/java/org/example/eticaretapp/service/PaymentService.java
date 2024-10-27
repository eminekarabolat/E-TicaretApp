package org.example.eticaretapp.service;

import org.example.eticaretapp.entity.enums.PaymentMethod;
import org.example.eticaretapp.repository.PaymentRepository;

public class PaymentService {
	private final PaymentRepository paymentRepository = new PaymentRepository();
	
	public void pay(Long userId, PaymentMethod paymentMethod){
		paymentRepository.pay(userId, paymentMethod);
	}
}