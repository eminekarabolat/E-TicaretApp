package org.example.eticaretapp.mapper;

import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.dto.request.FindComputerRequestDto;
import org.example.eticaretapp.dto.request.FindProductRequestDto;
import org.example.eticaretapp.dto.request.SimpleFindProductRequestDto;
import org.example.eticaretapp.entity.products.Product;
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
}