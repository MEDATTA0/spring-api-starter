package com.codewithmosh.store.carts.repositories;

import com.codewithmosh.store.carts.entities.CartItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CartItemRepository
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {}
