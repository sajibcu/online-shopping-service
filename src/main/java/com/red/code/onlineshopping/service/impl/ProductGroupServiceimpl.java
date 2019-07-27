package com.red.code.onlineshopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.red.code.onlineshopping.common.exceptions.EntityNotFoundException;
import com.red.code.onlineshopping.database.entity.model.ProductGroup;
import com.red.code.onlineshopping.repository.ProductGroupRepository;
import com.red.code.onlineshopping.service.ProductGroupService;
import com.red.code.onlineshopping.service.dto.ProductGroupDTO;

@Service
public class ProductGroupServiceimpl implements ProductGroupService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ProductGroupRepository productGroupRepository;
	
	@Autowired
	public ProductGroupServiceimpl(ProductGroupRepository productGroupRepository) {
		this.productGroupRepository = productGroupRepository;
	}

	@Override
	public List<ProductGroup> getProductGroup() {
		// TODO Auto-generated method stub
		log.debug("All product group");
		return productGroupRepository.findAll();
	}

	@Override
	public Page<ProductGroup> getProductGroupByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductGroup getProductGroupbyId(Long id) {
		// TODO Auto-generated method stub
		return productGroupRepository.findOneById(id).orElseThrow(() -> new EntityNotFoundException("No Product group with id: " + id));
	}

	@Override
	public ProductGroup createNewProductGroup(ProductGroupDTO productGroupDTO) {
		// TODO Auto-generated method stub
		ProductGroup productGroup = new ProductGroup();
		productGroup.setName(productGroupDTO.getName());
		return productGroupRepository.save(productGroup);
	}  

	@Override
	public void deleteProductGroup(Long id) {
		// TODO Auto-generated method stub
		ProductGroup productGroup = productGroupRepository.findOneById(id).orElseThrow(() -> new EntityNotFoundException("No user Product group with id: " + id));
		productGroupRepository.delete(productGroup);
		
	}

	@Override
	public ProductGroup editProductGroup(ProductGroupDTO productGroupDTO) {
		ProductGroup productGroup = productGroupRepository.findOneById(productGroupDTO.getId()).orElseThrow(() -> new EntityNotFoundException("No user Product group with id: " + productGroupDTO.getId()));
		productGroup.setName(productGroupDTO.getName());
		return productGroupRepository.save(productGroup);
	}
	

}
