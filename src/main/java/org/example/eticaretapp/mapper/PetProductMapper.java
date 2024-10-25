package org.example.eticaretapp.mapper;

import org.example.eticaretapp.entity.products.PetProduct;
import org.example.eticaretapp.view.Product.VwPetProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetProductMapper {

    PetProductMapper INSTANCE = Mappers.getMapper(PetProductMapper.class);

    VwPetProduct fromViewPetproduct(PetProduct petProduct);
}
