package com.codewithmosh.store.carts.repositories;

import com.codewithmosh.store.carts.entities.Cart;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CartRepository
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    @EntityGraph(attributePaths = "items")
    @Query("SELECT c FROM Cart c WHERE c.id = :cartId")
    Optional<Cart> getCarWithItems(@Param("cartId") UUID cartId);
}
