package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDetailsRepository extends JpaRepository<CartDetails,Long> {


    @Query("select c.productId from CartDetails c where c.shoppingCartId=?1")
    List<Long> findProductIdListByShoppingCardId(Long shoppingCartId);
	
	CartDetails findByShoppingCartIdAndProductId(Long shoppingCartId, Long productId);
}