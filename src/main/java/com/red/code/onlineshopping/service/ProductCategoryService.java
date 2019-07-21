package com.red.code.onlineshopping.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.red.code.onlineshopping.database.entity.model.ProductCategory;


public interface ProductCategoryService {
	
	List<ProductCategory> getProductCategory();

    Page<ProductCategory> getProductCategoryByPage(Pageable pageable);

    ProductCategory getProductCategorybyId(Long id);

    ProductCategory createNewProductCategory(ProductCategory productCategory);

    void deleteProductCategory(Long id);

    ProductCategory editProductCategory(ProductCategory productCategory);

}
