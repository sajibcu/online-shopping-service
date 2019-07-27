package com.red.code.onlineshopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.red.code.onlineshopping.common.exceptions.EntityNotFoundException;
import com.red.code.onlineshopping.database.entity.model.ProductCategory;
import com.red.code.onlineshopping.database.entity.model.ProductGroup;
import com.red.code.onlineshopping.repository.ProductCategoryRepository;
import com.red.code.onlineshopping.repository.ProductGroupRepository;
import com.red.code.onlineshopping.service.ProductCategoryService;
import com.red.code.onlineshopping.service.dto.ProductCategoryDTO;

@Service
public class ProductCategoryServiceimpl implements ProductCategoryService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ProductCategoryRepository productCategoryRepository;
	private ProductGroupRepository productGroupRepository;
	
	@Autowired
	public ProductCategoryServiceimpl(ProductCategoryRepository productCategoryRepository,ProductGroupRepository productGroupRepository) {
		this.productCategoryRepository = productCategoryRepository;
		this.productGroupRepository = productGroupRepository;
	}

	@Override
	public List<ProductCategory> getProductCategory() {
		// TODO Auto-generated method stub
		return productCategoryRepository.findAll();
	}

	@Override
	public Page<ProductCategory> getProductCategoryByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductCategory getProductCategorybyId(Long id) {
		// TODO Auto-generated method stub
		log.debug("get Category By Id: "+id);
		return productCategoryRepository.findOneById(id).orElseThrow(() -> new EntityNotFoundException("No Product Category with id: " + id));
	}

	@Override
	public ProductCategory createNewProductCategory(ProductCategoryDTO productCategoryDTO) {
		// TODO Auto-generated method stub
		ProductGroup productGroup=productGroupRepository.findOneById(productCategoryDTO.getProductGroup()).orElseThrow(() -> new EntityNotFoundException("No Product group with id: " + productCategoryDTO.getProductGroup()));
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(productCategoryDTO.getName());
		productCategory.setProductGroup(productGroup);
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public void deleteProductCategory(Long id) {
		
		// TODO Auto-generated method stub
		ProductCategory productCategory = productCategoryRepository.findOneById(id).orElseThrow(() -> new EntityNotFoundException("No Product Category with id: " + id));
		productCategoryRepository.delete(productCategory);
		
	}

	@Override
	public ProductCategory editProductCategory(ProductCategoryDTO productCategoryDTO) {
		// TODO Auto-generated method stub
		ProductCategory productCategory = productCategoryRepository.findOneById(productCategoryDTO.getId()).orElseThrow(() -> new EntityNotFoundException("No Product category with id: " + productCategoryDTO.getId()));
		ProductGroup productGroup=productGroupRepository.findOneById(productCategoryDTO.getProductGroup()).orElseThrow(() -> new EntityNotFoundException("No Product group with id: " + productCategoryDTO.getProductGroup()));
		productCategory.setName(productCategoryDTO.getName());
		productCategory.setProductGroup(productGroup);
		return productCategoryRepository.save(productCategory);
	}


}
