package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepository extends JpaRepository<CartDetails,Long> {
}
