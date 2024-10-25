package org.example.eticaretapp.mapper;

import org.example.eticaretapp.entity.products.Fashion;
import org.example.eticaretapp.view.Product.VwFashion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FashionMapper {

    FashionMapper INSTANCE = Mappers.getMapper(FashionMapper.class);

    VwFashion fromViewFashion(Fashion fashion);
}
