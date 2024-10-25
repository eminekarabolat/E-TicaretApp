package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.enums.petProducts.AnimalType;
import org.example.eticaretapp.entity.enums.petProducts.ProductType;
import org.example.eticaretapp.entity.products.PetProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetProductRepository extends JpaRepository<PetProduct,Long> {
    List<PetProduct> findAllByAnimalTypeInAndProductTypeInAndIdIn(List<AnimalType> animalTypes, List<ProductType> productTypes, List<Long> ids);
}