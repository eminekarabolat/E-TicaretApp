package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserIdAndIsDone(Long userId, Boolean isDone);

}
