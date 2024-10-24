package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.products.PetProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetProductRepository extends JpaRepository<PetProduct,Long> {
}