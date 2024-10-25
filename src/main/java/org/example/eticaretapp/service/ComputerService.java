package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.FindComputerRequestDto;
import org.example.eticaretapp.entity.enums.computer.Ram;
import org.example.eticaretapp.entity.products.Computer;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ComputerMapper;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.ComputerRepository;
import org.example.eticaretapp.view.Product.VwComputer;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		List<VwProducts> vwProductsList = new ArrayList<>();
		computers.forEach(computer -> {
			VwProducts vwProducts = ProductMapper.INSTANCE.fromFindComputerRequestDto(computer);
			vwProductsList.add(vwProducts);
		});
		return vwProductsList;

	}

	public VwComputer findById(Long productId) {
		Optional<Computer> optionalComputer = computerRepository.findById(productId);
		if (optionalComputer.isEmpty())
			throw new ETicaretException(ErrorType.NOTFOUND_COMPUTER);
		VwComputer vwComputer = ComputerMapper.INSTANCE.fromViewComputer(optionalComputer.get());
		return vwComputer;
	}
}