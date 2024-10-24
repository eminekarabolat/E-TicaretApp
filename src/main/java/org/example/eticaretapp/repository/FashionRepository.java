package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.products.Fashion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FashionRepository extends JpaRepository<Fashion, Long> {
}