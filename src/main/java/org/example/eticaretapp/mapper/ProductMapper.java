package org.example.eticaretapp.mapper;

import org.example.eticaretapp.dto.request.AddProductDto;
import org.example.eticaretapp.entity.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
	public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	// TODO @Mapping(target = "sku", expression = "java(UUID.randomUUID().toString())")
	Product fromAddProductDto(AddProductDto dto, Long sellerId, String sku);
	
}