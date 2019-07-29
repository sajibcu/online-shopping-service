package com.red.code.onlineshopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.code.onlineshopping.database.entity.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findOneById(Long id);
	
	List<Product> findAllByActivated(boolean activated);

}
