package com.red.code.onlineshopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.code.onlineshopping.database.entity.model.ProductGroup; 

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
	 Optional<ProductGroup> findOneById(Long id);
	

}
