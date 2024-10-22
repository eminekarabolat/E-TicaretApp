package org.example.eticaretapp.mapper;

import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ImageMapper {
	ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
	
	Image fromAddImageProductDto(AddImageMyProductRequestDto dto);
}