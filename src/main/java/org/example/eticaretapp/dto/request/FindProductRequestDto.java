package org.example.eticaretapp.dto.request;

import lombok.Builder;
import org.example.eticaretapp.entity.enums.computer.ComputerType;
import org.example.eticaretapp.entity.enums.computer.Harddisk;
import org.example.eticaretapp.entity.enums.computer.Ram;
import org.example.eticaretapp.entity.enums.fashion.Color;
import org.example.eticaretapp.entity.enums.fashion.FashionCategory;
import org.example.eticaretapp.entity.enums.fashion.Gender;
import org.example.eticaretapp.entity.enums.fashion.Size;
import org.example.eticaretapp.entity.enums.petProducts.AnimalType;
import org.example.eticaretapp.entity.enums.petProducts.ProductType;
import org.example.eticaretapp.entity.products.Computer;

import java.util.ArrayList;
import java.util.List;

public record FindProductRequestDto(
		Class<?> clazz,
		
		Double minPrice,
		Double maxPrice,
		List<String> brand,
		
		List<Color> colorList,
		List<FashionCategory> fashionCategories,
		List<Gender> genderList,
		List<Size> sizeList,
		List<AnimalType> animalTypeList,
		List<ProductType> productTypeList,
		List<ComputerType> computerTypeList,
		List<Harddisk> harddiskList,
		List<Ram> ramList
){
	
}