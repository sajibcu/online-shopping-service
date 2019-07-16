package com.red.code.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.code.onlineshopping.database.entity.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneById(Long id);
}
