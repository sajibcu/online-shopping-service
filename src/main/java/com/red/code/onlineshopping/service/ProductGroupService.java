package com.red.code.onlineshopping.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.red.code.onlineshopping.database.entity.model.ProductGroup;
import com.red.code.onlineshopping.service.dto.ProductGroupDTO;

public interface ProductGroupService {
	
	List<ProductGroup> getProductGroup();

    Page<ProductGroup> getProductGroupByPage(Pageable pageable);

    ProductGroup getProductGroupbyId(Long id);

    ProductGroup createNewProductGroup(ProductGroupDTO productGroupDTO);

    void deleteProductGroup(Long id);

    ProductGroup editProductGroup(ProductGroupDTO productGroupDTO);

}
