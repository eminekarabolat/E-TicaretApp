package org.example.eticaretapp.mapper;

import org.example.eticaretapp.dto.request.*;
import org.example.eticaretapp.entity.products.Computer;
import org.example.eticaretapp.entity.products.Fashion;
import org.example.eticaretapp.entity.products.PetProduct;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.view.VwProducts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
	public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	// TODO @Mapping(target = "sku", expression = "java(UUID.randomUUID().toString())")
	Product fromAddProductDto(AddProductDto dto, Long sellerId, String sku);
	
	@Mapping(target = "minPrice", defaultValue = "0.")
	@Mapping(target = "maxPrice", defaultExpression = "java(Double.MAX_VALUE)")
	@Mapping(target = "brand", defaultExpression = "java(new ArrayList<>())")
	SimpleFindProductRequestDto fromGeneralToSimpleDto(FindProductRequestDto dto);
	
	@Mapping(target = "computerTypeList", defaultExpression = "java(new ArrayList<>(List.of(ComputerType.values())))")
	@Mapping(target = "harddiskList", defaultExpression = "java(new ArrayList<>(List.of(Harddisk.values())))")
	@Mapping(target = "ramList", defaultExpression = "java(new ArrayList<>(List.of(Ram.values())))")
	FindComputerRequestDto fromGeneralToComputerDto(FindProductRequestDto dto);

	VwProducts fromFindComputerRequestDto(Computer computer);

	@Mapping(target = "fashionCategoryList", defaultExpression = "java(new ArrayList<>(List.of(FashionCategory.values())))")
	@Mapping(target = "colorList", defaultExpression = "java(new ArrayList<>(List.of(Color.values())))")
	@Mapping(target = "genderList", defaultExpression = "java(new ArrayList<>(List.of(Gender.values())))")
	@Mapping(target = "sizeList", defaultExpression = "java(new ArrayList<>(List.of(Size.values())))")
	FindFashionRequestDto fromFindFashionDto(FindProductRequestDto dto);

	VwProducts fromFindFashionRequestDto(Fashion fashion);

	@Mapping(target = "animalTypeList", defaultExpression = "java(new ArrayList<>(List.of(AnimalType.values())))")
	@Mapping(target = "productTypeList", defaultExpression = "java(new ArrayList<>(List.of(ProductType.values())))")
	FindPetProductRequestDto fromFindPetDto(FindProductRequestDto dto);

	VwProducts fromFindPetProductRequestDto(PetProduct petProduct);

}