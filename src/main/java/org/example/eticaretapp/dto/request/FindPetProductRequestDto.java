package org.example.eticaretapp.dto.request;

import org.example.eticaretapp.entity.enums.petProducts.AnimalType;
import org.example.eticaretapp.entity.enums.petProducts.ProductType;

import java.util.List;

public record FindPetProductRequestDto(
        List<AnimalType> animalTypeList,
        List<ProductType> productTypeList
) {
}
