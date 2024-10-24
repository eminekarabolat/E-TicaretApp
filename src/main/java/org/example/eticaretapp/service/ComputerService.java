package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.FindComputerRequestDto;
import org.example.eticaretapp.entity.enums.computer.Ram;
import org.example.eticaretapp.entity.products.Computer;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.repository.ComputerRepository;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {
	private final ComputerRepository computerRepository;
	
	public List<VwProducts> findComputers(FindComputerRequestDto dto,
	                                      List<Product> productList) {
		List<Long> ids = productList.stream().map(product -> product.getId()).toList();
		List<Computer> computers = computerRepository
				.findAllByRamInAndHarddiskInAndComputerTypeInAndIdIn(dto.ramList()
				, dto.harddiskList(), dto.computerTypeList(), ids);
		return null;
		
	}
}