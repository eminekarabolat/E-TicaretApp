package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    ShoppingCart find


}
