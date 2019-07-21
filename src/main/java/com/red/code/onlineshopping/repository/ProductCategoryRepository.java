package com.red.code.onlineshopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.code.onlineshopping.database.entity.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{
	 Optional<ProductCategory> findOneById(Long id);

}
