/**
 * 
 */
package com.red.code.onlineshopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.red.code.onlineshopping.common.exceptions.EntityNotFoundException;
import com.red.code.onlineshopping.database.entity.model.Product;
import com.red.code.onlineshopping.repository.ProductCategoryRepository;
import com.red.code.onlineshopping.repository.ProductGroupRepository;
import com.red.code.onlineshopping.repository.ProductRepository;
import com.red.code.onlineshopping.service.ProductCategoryService;
import com.red.code.onlineshopping.service.ProductGroupService;
import com.red.code.onlineshopping.service.ProductService;
import com.red.code.onlineshopping.service.dto.ProductDTO;

/**
 * @author Sajib
 *
 */
public class ProductServiceimpl implements ProductService{
	
	private ProductRepository productRepository;
	
	private ProductGroupService productGroupService;
	
	private ProductCategoryService productCategoryService;
	
	@Autowired
	public ProductServiceimpl(ProductRepository productRepository,ProductGroupService productGroupService,ProductCategoryService productCategoryService) {
		this.productRepository =productRepository;
		this.productGroupService = productGroupService;
		this.productCategoryService = productCategoryService;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductByActivatedStatus(boolean activated) {
		// TODO Auto-generated method stub
		return productRepository.findAllByActivated(activated);
	}

	@Override
	public Product getProductById(long id) {
		// TODO Auto-generated method stub
		return productRepository.findOneById(id).orElseThrow(()-> new EntityNotFoundException("No Product found with id :"+id));
	}

	@Override
	public Product addProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = new Product();
		processProduct(productDTO,product);
		return productRepository.save(product);
	}

	@Override
	public Product editProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = getProductById(productDTO.getId());
		processProduct(productDTO,product);
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		Product product = getProductById(id);
		productRepository.delete(product);
	}
	void processProduct(ProductDTO productDto,Product product)
	{
		product.setName(productDto.getName());
		
		product.setProductGroup(productGroupService.getProductGroupbyId(productDto.getProductGroupId()));
		
		product.setProductCategory(productCategoryService.getProductCategorybyId(productDto.getProductCategoryId()));
		
		product.setOrginalPrice(productDto.getOrginalPrice());
		product.setDiscountType(productDto.getDiscountType());
		product.setDiscount(productDto.getDiscount());
		product.setBuyPrice(productDto.getBuyPrice());
		product.setSelPrice(productDto.getSelPrice());
		product.setQuantity(productDto.getQuantity());
		product.setCaption(productDto.getCaption());
		product.setSearchKey(productDto.getSearchKey());
		product.setActivated(productDto.isActivated());
		
	}

}
