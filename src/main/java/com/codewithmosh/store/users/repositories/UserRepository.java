package com.codewithmosh.store.users.repositories;

import com.codewithmosh.store.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
